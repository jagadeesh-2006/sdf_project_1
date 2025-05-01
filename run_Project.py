import subprocess
import sys

def build_and_run(args):
    # Compile Java files into 'out' directory
    subprocess.run([
        "javac", "-d", "out",
        "arbitraryarithmetic/Afloat.java",
        "arbitraryarithmetic/Ainteger.java",
        "MyInfArith.java"
    ], check=True)

    # Run the main class using classpath 'out'
    subprocess.run(["java", "-cp", "out", "MyInfArith"] + args)

if __name__ == "__main__":
    if len(sys.argv) < 5:
        print("Usage: python run_project.py <int/float> <add/sub/mul/div> <operand1> <operand2>")
    else:
        build_and_run(sys.argv[1:])
