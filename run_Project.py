
import subprocess
import sys
import os

def build_and_run(args):
    # Create output directory if it doesn't exist
    os.makedirs("out", exist_ok=True)

    # Compile Java files to 'out'
    subprocess.run([
        "javac", "-d", "out",
        "arbitraryarithmetic/Afloat.java",
        "arbitraryarithmetic/Ainteger.java",
        "MyInfArith.java"
    ], check=True)

    # Run the main class with classpath set to 'out'
    subprocess.run(["java", "-cp", "out", "MyInfArith"] + args)

if __name__ == "__main__":
    if len(sys.argv) < 5:
        print("Usage: python run_project.py <int/float> <add/sub/mul/div> <operand1> <operand2>")
    else:
        build_and_run(sys.argv[1:])

