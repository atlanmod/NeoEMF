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
# docker build -t "neoemf-benchmarks" --build-arg branch="backend-abstraction" .
# docker run -it "neoemf-benchmarks" bash
# java -jar benchmarks.jar [options]
#

FROM ubuntu:latest

# Optional arguments
ARG branch=master

# Define the working directory
WORKDIR /root

# Install dependencies
RUN apt-get update \
 && apt-get install -y \
    openjdk-8-jdk \
    git \
    maven \
 && rm -rf /var/lib/apt/lists/*

# Clone the project from Github
RUN git clone -b $branch https://github.com/atlanmod/NeoEMF.git neoemf

# Build project & benchmarks
RUN mvn -B install -DskipTests -Dmaven.javadoc.skip=true -f neoemf \
 && mvn -B package -DskipTests -Dmaven.javadoc.skip=true -f neoemf/benchmarks

# Copy the resulting artifacts & remove build files
RUN cp -R neoemf/benchmarks/core/target/exec/* . \
 && rm -rf neoemf \
 && rm -rf .m2

# Remove dependencies
RUN apt-get purge --auto-remove -y \
    git \
    maven
