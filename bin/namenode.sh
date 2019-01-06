#!/usr/bin/env bash

namenode_ip = "140.143.17.209"
namenode_path = "/home/ubuntu/namenode"
rpc_port = 1234
replication_factor = 3

java -jar root.jar ${namenode_ip} ${namenode_path} ${rpc_port} ${replication_factor}
