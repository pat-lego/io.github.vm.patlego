# System OCR

This module allows users to convert file types to other formats.

## Services

1. OCRService

        The OCRService allows users to specify input type, output type and the renderer.
        1. Input Type: MIME Type of the incoming document
        2. Output Type: MIME Type of the outgoing document
        3. Renderer: Apache Tika, PDF Box

2. ContentTypeService

    The ContentTypeService allows users to determine the content type of a document