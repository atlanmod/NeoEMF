#
# Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
#
# All rights reserved. This program and the accompanying materials are made
# available under the terms of the Eclipse Public License v2.0 which accompanies
# this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
#

#
# USAGE:
#
# docker build -t neoemf .
# docker run -v {LOCAL_DIR}:/root/ws neoemf [parameters] init
# docker run -v {LOCAL_DIR}:/root/ws neoemf [parameters] [options]
#

FROM maven:alpine

ENV NEOEMF_HOME /root/ws

WORKDIR /root

ADD . src

RUN mvn -B install -DskipTests -Dmaven.javadoc.skip=true -pl !neoemf-data/hbase,!neoemf-tests -f src/pom.xml \
 && mvn -B package -DskipTests -Dmaven.javadoc.skip=true -f src/benchmarks/pom.xml -Duberjar.directory=/root/ \
 && rm -rf src .m2/* /tmp/* /var/tmp/*

CMD ["-help"]
ENTRYPOINT ["java", "-jar", "benchmarks.jar"]