# ArbitraryArithmetic

## 📌 Overview

**ArbitraryArithmetic** is a Java-based library and command-line tool designed to handle **infinite precision** arithmetic for both integers and floating-point numbers.  
This project implements custom arithmetic logic using strings, overcoming limitations of Java's built-in numeric types.

---

## 🚀 Features

- Infinite-precision arithmetic operations:
  - ✅ Integer support: `Ainteger.java`
  - ✅ Floating-point support: `Afloat.java`
- Basic operations:
  - ➕ Addition
  - ➖ Subtraction
  - ✖️ Multiplication
  - ➗ Division
- CLI Interface via `MyInfArith.java`
- Example programs: `example.java`, `MainExample.java`
- Python helper script for build & run: `run_Project.py`
- Apache Ant-based build system: `build.xml`
- Docker support: `dockerfile`

---

## 🛠️ Project Structure

.
├── arbitraryarithmetic/
│ ├── Afloat.java # Floating-point arithmetic implementation
│ ├── Ainteger.java # Integer arithmetic implementation
│ ├── example.java # Example test cases (optional)
│ └── MainExample.java # Alternate main for demos/tests
├── build.xml # Ant build configuration
├── dockerfile # Dockerfile to run the project in container
├── MyInfArith.java # Main CLI interface
└── run_Project.py # Python helper script for build/run





---

## 🔧 Build & Run

### Option 1: Apache Ant


ant compile        # to compile

ant run -Darg0=<int|float> -Darg1=<add|sub|mul|div> -Darg2=<num1> -Darg3=<num2>        # to run

ant clean     


#for python

python3 run_Project.py    # jsut to execute

python3 run_Project.py float div 12345.678 12.34        #to run


#for docker

Docker images      # to see what are the images

docker build -t arbitrary-arith -f dockerfile .           ## to build an image
 
docker run --rm arbitrary-arith int mul 999999999 888888888       ## to run 

