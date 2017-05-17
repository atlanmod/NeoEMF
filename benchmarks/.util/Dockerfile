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

FROM debian:latest

# Optional arguments
ARG branch=master

# Define the working directory
WORKDIR /root

# Install JDK & dependencies
RUN apt-get update \
 && apt-get install --no-install-recommends -y software-properties-common \
 && add-apt-repository -y "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" \
 && apt-get update \
 && echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | /usr/bin/debconf-set-selections \
 && apt-get install --no-install-recommends -y \
    oracle-java8-installer \
    git \
    maven \
 && rm -rf /var/lib/apt/lists/*

# Clone the project from Github
RUN git clone -b $branch https://github.com/atlanmod/NeoEMF.git neoemf

# Build project & benchmarks
RUN mvn -B install -DskipTests -Dmaven.javadoc.skip=true -f neoemf/pom.xml \
 && mvn -B package -DskipTests -Dmaven.javadoc.skip=true -f neoemf/benchmarks/pom.xml

# Copy the resulting artifacts & remove build files
RUN cp -R neoemf/benchmarks/core/target/exec/* . \
 && rm -rf neoemf .m2

# Remove dependencies
RUN apt-get purge --auto-remove -y \
    git \
    maven
