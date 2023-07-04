using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BillingSystem.Audit.Tools
{
    internal class TableSchema
    {
        private string _tblName;
        public string TableName
        {
            get { return _tblName; }
            set { _tblName = value; }
        }
        
        private List<string> _columns;
        public List<string> Columns
        {
            get {
                if (_columns == null)
                    _columns = new List<string>();

                return _columns;
            }
            set { _columns = value; }
        }

        public TableSchema() { }
        public TableSchema(string tableName) {
            _tblName = tableName;
        }

        public override string ToString()
        {
            return this._tblName;
        }
    }
}
