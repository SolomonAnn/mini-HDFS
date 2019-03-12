# mini-HDFS

# Overview

It's a mini version of HDFS.

# Prerequisites

To use mini-HDFS, you need to have (the latest version is recommended):

1. Java
2. Maven protobuf gRPC (If you want to compile and install mini-HDFS from source code)

## Usage

Create ```namenode``` and ```datanode``` directories to store your relevant files. 

``` bash
mkdir namenode
mkdir datanode
```

Then fill in  ```config.xml``` with your directories, ips and ports. 

The next step is to enter into ```bin``` diretory and start ```namenode```, ```datanode``` and ```client``` in different sites.

``` bash
sh namenode.sh
sh datanode.sh
sh client.sh
```

## Build

**// TODO**
