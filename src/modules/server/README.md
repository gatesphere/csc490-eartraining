# Ear Training Server

Jacob Peck

## Overview

This server provides both authentication and datalogging functionality, in a threadsafe,
concurrent manner, with the ability to restrict concurrent connections.

## Execution

To compile:

    javac ETServer.java
    
To run:

    java ETServer <port_number> <max_connections>
    
where `<port_number>` is an integer port number (ranging from 1024-65535), and 
`<max_connections>` is the maximum number of concurrent connections, or 0 for 
no maximum.  A reasonable value for this parameter is the number of cores on the
server box.

## Authentication

To authenticate a user, send a message in this format:

    USERAUTH
    <username>
    <password>
    <empty line>

If the user does not exist in the database, they will be added automatically (auto-registration)
and the server will return a message of `SUCCESS`.  If the user exists and the password is correct,
the server will return a message of `SUCCESS`.  If the user exists but the password is incorrect,
the server will return a message of `FAILURE`.

## Data reporting

To report data, send a message in this format:

    XMLDUMP
    <xml data here>
    <empty line>

The server will then automatically save the data as an XML file in the `xml` directory.

## Incorrectly formatted request

The server will simply ignore any messages that don't start with `XMLDUMP` or `USERAUTH`.

## Terminating the server

To terminate the server safely, send a Ctrl-C message to the server's process.  The 
user database will be written to file, and the server will exit.

## TestClient.java

This is a unit test for the server.
