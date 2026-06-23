package com.enterpriseai.document.chunk;

import java.util.List;

public interface DocumentChunker {

    List<String> chunk (String text);
}
