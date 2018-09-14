@echo off
cd lib
start java -cp hsqldb.jar org.hsqldb.Server -database db/test
start java -cp hsqldb.jar org.hsqldb.util.DatabaseManager