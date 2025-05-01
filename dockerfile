
FROM openjdk:21

# Set working directory
WORKDIR /app

# Copy Java source files
COPY arbitraryarithmetic/ ./arbitraryarithmetic/
COPY MyInfArith.java ./

# Compile and keep package structure
RUN javac -d . arbitraryarithmetic/*.java MyInfArith.java

ENTRYPOINT ["java", "-cp", ".", "MyInfArith"]
