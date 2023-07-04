using System;
using System.Data;
using System.Data.Common;
using System.Configuration;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.IO;
using System.Text;
using System.Linq;

using DDTek.Oracle;

namespace BillingSystem.Audit.Tools
{
    internal class AuditTrigger
    {
        public event EventHandler LoadDBSchemas;
        public event EventHandler GeneratingTable;
        public event EventHandler Completed;

        public void Start() {
            List<TableSchema> lstSchemas = getTableSchemas();

            buildScript(lstSchemas);

            if (Completed != null)
                Completed(lstSchemas.Count, EventArgs.Empty);
        }

        private List<TableSchema> getTableSchemas() {
            string connectionString = ConfigurationManager.ConnectionStrings["BillingSystem"].ConnectionString;
            OracleConnection connection = new OracleConnection(connectionString);
            OracleCommand cmd = new OracleCommand();
            cmd.Connection = connection;
            cmd.CommandText = "SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_LENGTH FROM USER_TAB_COLS WHERE TABLE_NAME ";
            cmd.CommandText += "IN (SELECT OBJECT_NAME FROM USER_OBJECTS WHERE OBJECT_TYPE = 'TABLE') ORDER BY TABLE_NAME";

            DataTable tableSchemas = new DataTable("LIST_OF_TABLES");
            OracleDataAdapter adapter = new OracleDataAdapter(cmd);
            
            if (LoadDBSchemas != null)
                LoadDBSchemas(this, EventArgs.Empty);

            adapter.Fill(tableSchemas);
            adapter.Dispose();
            adapter = null;

            List<string> includeTables = getTables();
            List<TableSchema> lstTables = new List<TableSchema>();
            Dictionary<string, TableSchema> schema = new Dictionary<string,TableSchema>();
            foreach (DataRow row in tableSchemas.Rows) {
                string tblName = row["TABLE_NAME"].ToString();
                if (!includeTables.Contains(tblName)
                    || row["DATA_TYPE"].ToString() == "BLOB") continue;

                if (!schema.ContainsKey(tblName)) {
                    TableSchema tblSchema = new TableSchema(tblName);
                    schema.Add(tblName, tblSchema);
                    lstTables.Add(tblSchema);
                }

                TableSchema tmpSchema = schema[tblName];
                tmpSchema.Columns.Add(row["COLUMN_NAME"].ToString());
	        }
            tableSchemas.Clear();
            tableSchemas = null;

            schema.Clear();
            schema = null;

            GC.Collect();

            return lstTables;
        }

        private void buildScript(List<TableSchema> lstSchemas) {
            using (System.IO.StreamWriter sw = new StreamWriter("BillingSystem.AuditTrail.Create.sql", false, Encoding.ASCII)) {
                string templateTrigger = ConfigurationManager.AppSettings["TriggerCommand"];
                string templateBody1 = ConfigurationManager.AppSettings["TriggerBody1"];
                string templateBody2 = ConfigurationManager.AppSettings["TriggerBody2"];

                using (System.IO.StreamWriter swDrop = new StreamWriter("BillingSystem.AuditTrail.Drop.sql", false, Encoding.ASCII)) {
                    List<string> lstExFields = getExcludeFields();
                    List<TableSchema> lstSpec = getSpecialExcludeFields();

                    foreach (TableSchema tbl in lstSchemas) {
                        if (GeneratingTable != null)
                            GeneratingTable(tbl.TableName, EventArgs.Empty);

                        // Special fields
                        TableSchema tblSpec = lstSpec.SingleOrDefault(s => s.TableName == tbl.TableName);

                        // Build trigger's body
                        string triggerBody1 = string.Empty;
                        string triggerBody2 = string.Empty;
                        foreach (string field in tbl.Columns) {
                            // Common fields
                            if (lstExFields.Contains(field)) continue;
                            // Special fields
                            if (tblSpec != null)
                                if (tblSpec.Columns.Contains(field)) continue;

                            triggerBody1 += string.Format(templateBody1, field);
                            triggerBody2 += string.Format(templateBody2, tbl.TableName, field);
                        }

                        // Build trigger
                        string triggerCommand = string.Format(templateTrigger, tbl.TableName, triggerBody1, triggerBody2);
                        sw.WriteLine(triggerCommand);

                        string templateDropTrigger = ConfigurationManager.AppSettings["DropTriggerCommand"];
                        swDrop.WriteLine(string.Format(templateDropTrigger, tbl.TableName));

                        System.Threading.Thread.Sleep(10);
                    }
                }
            }
        }

        private List<string> getTables() {
            string tables = ConfigurationManager.AppSettings["GenForTables"];
            tables = tables.Replace("\r", "");
            tables = tables.Replace("\n", "");
            tables = tables.Replace(" ", "");

            List<string> lstTables = new List<string>();
            lstTables.AddRange(tables.Split(new string[] {","}, StringSplitOptions.RemoveEmptyEntries));

            return lstTables;
        }

        private List<string> getExcludeFields()
        {
            List<string> lstFields = new List<string>();
            string fields = ConfigurationManager.AppSettings["ExcludeAllFields"];

            if (!string.IsNullOrEmpty(fields))
            {
                fields = fields.Replace("\r", "");
                fields = fields.Replace("\n", "");
                fields = fields.Replace(" ", "");
                lstFields.AddRange(fields.Split(new string[] { "," }, StringSplitOptions.RemoveEmptyEntries));
            }

            return lstFields;
        }

        private List<TableSchema> getSpecialExcludeFields()
        {
            List<TableSchema> lstSchemas = new List<TableSchema>();
            NameValueCollection excluding = ConfigurationManager.GetSection("Excluding") as NameValueCollection;
            if (excluding != null) foreach (string tbl in excluding.AllKeys)
            {
                TableSchema schema = new TableSchema(tbl);
                lstSchemas.Add(schema);
                List<string> lstFields = new List<string>();
                schema.Columns = lstFields;

                string fields = excluding[tbl];
                fields = fields.Replace("\r", "");
                fields = fields.Replace("\n", "");
                fields = fields.Replace(" ", "");
                lstFields.AddRange(fields.Split(new string[] { "," }, StringSplitOptions.RemoveEmptyEntries));
            }

            return lstSchemas;
        }
    }
}
