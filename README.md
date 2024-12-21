# Huffman Compression Analysis 

Name: Armia Joseph Hakim
Id: 21010229

Here is the analysis of my implementation for huffman in comparison to 7-zip for n ranging from 1 to 5

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

# Result Explanation

- The compression ratio and the compression time are directly proportional as the majorty of the code time is in the reading/writing so when the ratio increase the time also increase.
- I have implemented a special solution for the n = 1 so the time taken on that value is significantly lower than the other values of n
- In terms of time of compression, for large files my algorithm runs better than 7-zip
- But in terms of compression ratio, the 7-zip is better (may use another approaches other than huffman coding)

# Resources 
I have written in the header of each class what resources I had used from the internet. Here is a summary of all resources so far
<br><br>
 Buffered Input Stream
 <br>
 <a href="https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html">
 Oracle Docs
 </a><br>
 <a href="https://www.geeksforgeeks.org/java-io-bufferedinputstream-class-java/">
 Geeks for Geeks
 </a>
 <br><br>
 Random Access File
 <br>
 <a href="https://www.digitalocean.com/community/tutorials/java-randomaccessfile-example">
 Digital Ocean
 </a><br>
 <a href="https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html">
 Oracle Docs
 </a>
 <br><br>
 Base64
 <br>
 <a href="https://www.baeldung.com/java-base64-encode-and-decode">
 Baeldung
 </a><br>
 <a href="https://www.geeksforgeeks.org/basic-type-base64-encoding-and-decoding-in-java/">
 Geeks for Geeks
 </a>
 <br><br>
 HashMap compute
 <br>
 <a href="https://stackoverflow.com/questions/70430071/for-hashmap-is-it-more-efficient-to-use-compute-or-put">
 StackOverFlow
 </a>
 <br><br>
 File Channel
 <br>
 <a href="https://stackoverflow.com/questions/33601905/read-write-using-filechannel-and-fileinput-outputstream-java">
 StackOverFlow
 </a><br>
 <a href="https://www.geeksforgeeks.org/fileinputstream-getchannel-method-in-java-with-examples/">
 Geeks for Geeks
 </a><br>
 <a href="https://www.baeldung.com/java-filechannel">
 Baeldung
 </a>
 <br><br>
 Try with Resources
 <br>
 <a href="https://www.geeksforgeeks.org/try-with-resources-feature-in-java/">
 Geeks for Geeks
 </a>
 <br><br>
 Digit Regex
 <br>
 <a href="https://stackoverflow.com/questions/2841550/what-does-d-mean-in-a-regular-expression">
 StackOverFlow
 </a>
 <br><br>
 Also, I have reused my implementation of Min Heap from Data Structures and Algorithms Course


# Acknowledgement

I acknowledge that I am aware of the academic integrity guidelines of this course, and that I worked on this assignment independently without any unauthorized help
<br>
Armia Joseph Hakim - 21010229
