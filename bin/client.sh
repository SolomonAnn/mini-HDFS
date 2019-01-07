#!/bin/bash

namenode_ip=`grep -o "<namenode_ip>[^<]*</namenode_ip>" config.xml | sed -e "s/<namenode_ip>\(.*\)<\/namenode_ip>/\1/"`
namenode_path=`grep -o "<namenode_path>[^<]*</namenode_path>" config.xml | sed -e "s/<namenode_path>\(.*\)<\/namenode_path>/\1/"`
datanode_ip=`grep -o "<datanode_ip>[^<]*</datanode_ip>" config.xml | sed -e "s/<datanode_ip>\(.*\)<\/datanode_ip>/\1/"`
datanode_path=`grep -o "<datanode_path>[^<]*</datanode_path>" config.xml | sed -e "s/<datanode_path>\(.*\)<\/datanode_path>/\1/"`
rpc_port=`grep -o "<rpc_port>[^<]*</rpc_port>" config.xml | sed -e "s/<rpc_port>\(.*\)<\/rpc_port>/\1/"`
socket_port=`grep -o "<socket_port>[^<]*</socket_port>" config.xml | sed -e "s/<socket_port>\(.*\)<\/socket_port>/\1/"`

java -cp root.jar cn.edu.tsinghua.hdfs.client.DFSClient ${namenode_ip} ${namenode_path} ${datanode_ip} ${datanode_path} ${rpc_port} ${socket_port}
