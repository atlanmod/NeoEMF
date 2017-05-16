FROM ubuntu:latest

WORKDIR /root

#####
# Install common dependencies from packages
#####

RUN apt-get --quiet=2 update

RUN apt-get --quiet=2 --yes install \
    git \
    openjdk-8-jdk \
    maven

#####
# Clone the project from Github
#####

RUN git clone --quiet https://github.com/atlanmod/NeoEMF.git neoemf

#####
# Build the project
#####

RUN mvn -B --quiet install -DskipTests -Dmaven.javadoc.skip=true -f neoemf

RUN mvn -B --quiet package -DskipTests -Dmaven.javadoc.skip=true -f neoemf/benchmarks