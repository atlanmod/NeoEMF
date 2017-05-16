FROM ubuntu:latest

WORKDIR /root

#####
# Install common dependencies from packages
#####

RUN apt-get -q update

RUN apt-get -q install -y \
    git \
    openjdk-8-jdk \
    maven

#####
# Clone the project from Github
#####

RUN git clone https://github.com/atlanmod/NeoEMF.git neoemf

#####
# Build the project
#####

RUN mvn -B install -DskipTests -Dmaven.javadoc.skip=true -f neoemf

RUN mvn -B package -DskipTests -Dmaven.javadoc.skip=true -f neoemf/benchmarks