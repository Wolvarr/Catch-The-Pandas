using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProtoTest
{
    class Program
    {

        static void Main(string[] args)
        {

            //Deletes all previous test outputs
            System.IO.DirectoryInfo di = new DirectoryInfo("testfiles\\testoutput");

            foreach (FileInfo file in di.GetFiles())
            {
                file.Delete();
            }


            Console.WriteLine("          Test scenarios for Prototype\n\n\n");
            Console.WriteLine("     This program simply compare the output \n    of each tests with their expected outputs");
            Console.WriteLine("\n\n Press any key to start!");


            Console.ReadKey();


            int succeeded = 0;
            int numOfTests = 10;
            string testMap;
            string testCommands;
            string testOut;

            for (int currentTest = 0; currentTest < numOfTests; currentTest++)
            {

                testMap = "testmap" + currentTest;
                testCommands = "testcommands" + currentTest;
                testOut = "testoutput" + currentTest;


                var processInfo = new ProcessStartInfo("C:\\Program Files\\Java\\jdk-12\\bin\\java.exe", "-jar testfiles\\catch-the-pandas.jar \\testfiles\\maps\\" + testMap + "\\ \\testfiles\\commands\\" + testCommands + "\\ \\testfiles\\testoutput\\output" + currentTest + ".txt")
                {
                    WindowStyle = ProcessWindowStyle.Maximized,
                    CreateNoWindow = false,
                    UseShellExecute = false
                };
                Process proc;

                if ((proc = Process.Start(processInfo)) == null)
                {
                    throw new InvalidOperationException("??");
                }

                proc.WaitForExit();
                int exitCode = proc.ExitCode;
                proc.Close();

              /*  bool areEquals = System.IO.File.ReadLines("testfiles\\testoutput\\output" + currentTest + ".txt").SequenceEqual(
                System.IO.File.ReadLines("testfiles\\reference\\output" + currentTest + ".txt"));

                Console.WriteLine("\nTest " + (currentTest + 1) + ": Calculating result");
                for (int i = 0; i < 17; i++)
                {
                    Console.Write("_");
                    System.Threading.Thread.Sleep(150);
                }
                Console.WriteLine("");


                if (areEquals)
                {
                    Console.WriteLine("Test " + (currentTest + 1) + ": SUCCEEDED\n_________________\n");
                    succeeded++;
                }

                else
                {
                    Console.WriteLine("Test " + (currentTest + 1) + ": FAILED\n_________________\n");

                } */

                if (currentTest < numOfTests - 1)
                {
                    Console.WriteLine("\nPress any key to continue\n\n");
                    Console.ReadKey();
                }

            }


            Console.WriteLine("\n\nTEST FINISHED");
            Console.WriteLine("   (" + succeeded + "/" + numOfTests + ") tests was succesful");
            Console.WriteLine("\n\nPress any key to exit");
            Console.ReadKey();
        }
    }
}
