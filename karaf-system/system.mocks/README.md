# System Mocks

This module is used for testing purposes only. It allows developers to test certain pieces of functionality that do not necessarily exist yet.

## Use Case

Currently the `maven-bundle-plugin` allows users to generate OCR metadata which can be used for unit tests that leverage the Apache Sling Mocks. The issue that occurs is when an interface is used in other modules other then itself, which means that you cannot test it since an implementation does not exist. 

Thus this module provides the project with sample implementations that can be tested in a mock OSGi environment prior to releasing the application