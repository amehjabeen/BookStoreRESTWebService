1. The main webservice implementation is present in the BookstoreServicesRS folder.
2. The Representation and Request classes are separated out into a library project called the BookstoreLibrary.
3. BookstoreLibrary.jar was exported from the library project and added to the buildpath of the webservice so that it can be used by other clients as well.

4. For making the representations compatible with .NET a BookstoreLibrary.dll was created but we did not use that in the project since .NET does not support Jackson annotations. Instead we chose to deal with it the easier way by just parsing XML and JSON responses instead.

5. The client code is located at: https://github.com/danielbednarczyk/WebClientApp

