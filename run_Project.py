import subprocess
import sys

def build_and_run(args):
    # Compile Java files
    subprocess.run(["javac", "-d", "out", "arbitraryarithmetic/*.java", "MyInfArith.java"], check=True)

    # Create JAR file
    subprocess.run([
        "jar", "cf", "arithmetic.jar",
        "-C", "out", "arbitraryarithmetic",
        "-C", "out", "MyInfArith.class"
    ], check=True)

    # Run the main class
    subprocess.run(["java", "-cp", "arithmetic.jar", "MyInfArith"] + args)

if __name__ == "__main__":
    if len(sys.argv) < 5:
        print("Usage: python run_project.py <int/float> <add/sub/mul/div> <operand1> <operand2>")
    else:
        build_and_run(sys.argv[1:])
