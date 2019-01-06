#!/usr/bin/env bash

datanode_ip = "58.87.126.138"
datanode_path = "/home/ubuntu/datanode"
socket_port = 1111

java -cp root.jar cn.edu.tsinghua.hdfs.server.datanode.Datanode ${datanode_ip} ${datanode_path} ${socket_port}