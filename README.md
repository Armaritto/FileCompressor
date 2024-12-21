# Huffman Compression Analysis 

## gbbct10.seq (1GB file .seq file)

|  n  | Size | Compression Ratio | Compression Time | Decompression Time | Status  |
|:---:|:----:|:-----------------:|:----------------:|:------------------:|:-------:|
|  1  | 1 GB |        52%        |      112 s       |       195 s        | correct |
|  2  | 1 GB |        42%        |      169 s       |       158 s        | correct |
|  3  | 1 GB |        37%        |      144 s       |       157 s        | correct |
|  4  | 1 GB |        35%        |      168 s       |       156 s        | correct |
|  5  | 1 GB |        36%        |      213 s       |       232 s        | correct |
|7 zip| 1 GB |        18%        |      390 s       |        9 s         | correct |

## gbbct10_old.seq (473 MB .seq file)

|  n  |  Size  | Compression Ratio | Compression Time | Decompression Time | Status  |
|:---:|:------:|:-----------------:|:----------------:|:------------------:|:-------:|
|  1  | 473 MB |        50%        |       33 s       |        60 s        | correct |
|  2  | 473 MB |        41%        |       56 s       |        50 s        | correct |
|  3  | 473 MB |        37%        |       46 s       |        50 s        | correct |
|  4  | 473 MB |        35%        |       52 s       |        64 s        | correct |
|  5  | 473 MB |        43%        |       78 s       |        84 s        | correct |
|7 zip| 473 MB |        18%        |       206 s      |        2 s         | correct |

## greedy.pdf (806 KB .pdf file)

|  n  |  Size  | Compression Ratio | Compression Time | Decompression Time | Status  |
|:---:|:------:|:-----------------:|:----------------:|:------------------:|:-------:|
|  1  | 806 KB |        93%        |      98 ms       |       153 ms       | correct |
|  2  | 806 KB |       175%        |      561 ms      |       283 ms       | correct |
|  3  | 806 KB |       331%        |      588 ms      |       381 ms       | correct |
|  4  | 806 KB |       335%        |      469 ms      |       284 ms       | correct |
|  5  | 806 KB |       263%        |      358 ms      |       207 ms       | correct |
|7 zip| 806 KB |        70%        |       3 s        |        2 s         | correct |
