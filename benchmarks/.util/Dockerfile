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
# docker build -t "neoemf-benchmarks" --build-arg branch="backend-abstraction" https://raw.githubusercontent.com/atlanmod/NeoEMF/backend-abstraction/benchmarks/.util/Dockerfile
# docker run -it "neoemf-benchmarks" bash
# cd /root/neoemf/benchmarks/core/target/exec
# java -jar benchmarks.jar [options]
#

FROM ubuntu:latest

# Optional arguments
ARG branch=master

# Define the working directory
WORKDIR /root

# Install dependencies
RUN apt-get update && apt-get install -y git openjdk-8-jdk maven && rm -rf /var/lib/apt/lists/*

# Clone the project from Github
RUN git clone -b $branch https://github.com/atlanmod/NeoEMF.git neoemf

# Build project
RUN mvn -B install -DskipTests -Dmaven.javadoc.skip=true -f neoemf

# Build benchmarks
RUN mvn -B package -DskipTests -Dmaven.javadoc.skip=true -f neoemf/benchmarks
