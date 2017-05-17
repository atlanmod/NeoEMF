#
# Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
#

#
# USAGE:
#
# docker build -t "neoemf-benchmarks" .
# docker run -it "neoemf-benchmarks" bash
# java -jar benchmarks.jar [options]
#

FROM debian:latest

# Define the working directory
WORKDIR /root

# Variables
ENV SRC='src'

# Install common dependency
RUN apt-get update -q \
 && apt-get install -q --no-install-recommends -y \
    software-properties-common \

# Add the JDK8 repository
 && add-apt-repository -y 'deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main' \
 && apt-get update -q \

# Auto-accept the Oracle JDK license
 && echo 'oracle-java8-installer shared/accepted-oracle-license-v1-1 select true' | /usr/bin/debconf-set-selections \

# Install JDK8 & build tool
 && apt-get install -q --no-install-recommends -y \
    oracle-java8-installer \
    oracle-java8-set-default \
    maven \

# Clean the apt cache
 && rm -rf /var/lib/apt/lists/*

# Copy the project from Github (using wget to limit space consumption)
ADD . ${SRC}

# Build the main project
RUN mvn -B install -DskipTests -Dmaven.javadoc.skip=true -f ${SRC}/pom.xml

# Build benchmarks
RUN mvn -B package -DskipTests -Dmaven.javadoc.skip=true -f ${SRC}/benchmarks/pom.xml

# Move the resulting artifacts
RUN mv -f ${SRC}/benchmarks/core/target/exec/* . \

# Remove build files
 && rm -rf ${SRC} .m2 /tmp/*
