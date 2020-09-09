# System Profile

This module allows developers to develop a Single Page Applications (SPA) and deploy them to the VM. 

Currently the VM is hosting a Vue.js application and it can be viewed via HTTP Whiteboard protocol exposed via OSGi.

## Architecture

Leveraging the [`frontend-maven-plugin`](https://github.com/eirslett/frontend-maven-plugin) users can develop a local Node project within this module and deploy it within the VM.

This prevents users from having to write various CORS filters and filters that massage the requests between the client and the server.

