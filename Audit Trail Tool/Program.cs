using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BillingSystem.Audit.Tools
{
    class Program
    {
        static void Main(string[] args)
        {
            AuditTrigger audit = new AuditTrigger();
            audit.LoadDBSchemas += new EventHandler(audit_LoadDBSchemas);
            audit.GeneratingTable += new EventHandler(audit_GeneratingTable);
            audit.Completed += new EventHandler(audit_Completed);

            audit.Start();
        }

        static void audit_Completed(object sender, EventArgs e)
        {
            Console.WriteLine();

            Console.WriteLine("Generates {0} table(s) Completed Successfull.", sender);
            Console.WriteLine("Press <Enter> to exit...");
            Console.ReadLine();
        }

        static void audit_GeneratingTable(object sender, EventArgs e)
        {
            Console.WriteLine("Generating trigger for table {0}...", sender);
        }

        static void audit_LoadDBSchemas(object sender, EventArgs e)
        {
            Console.WriteLine("Loading schema...");
        }
    }
}
