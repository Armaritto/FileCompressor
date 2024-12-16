# File Compressor

#### `n = 1`
|   Testcase    | Type  |   size   | Compression Ratio | Compression Time | Decompression Time | Correctness |
|:-------------:|-------|:--------:|:-----------------:|:----------------:|:------------------:|:-----------:|
|      new      | .txt  | 10.1 MB  |        74%        |      3 sec       |       5 sec        |     ✔️      |
| largeTestFile | .txt  |  1.0 GB  |        74%        |     276 sec      |      487 sec       |     ✔️      |
|    gbbct10    | .seq  |  1.5 GB  |        52%        |     442 sec      |      554 sec       |     ✔️      |
|    greedy     | .pdf  | 825.4 KB |        93%        |    0.753 sec     |      0.83 sec      |     ✔️      |
|   up-movie    | .jpg  | 75.9 KB  |       104%        |    0.197 sec     |     0.128 sec      |     ✔️      |
|    parking    | .jpeg | 101.6 KB |       103%        |     0.22 sec     |     0.164 sec      |     ✔️      |
|    discord    | .deb  | 101.3 MB |       100%        |      12 sec      |       22 sec       |     ✔️      |
|   RickRoll    | .mp4  | 19.4 MB  |        99%        |      2 sec       |       4 sec        |     ✔️      |
|   Despacito   | .mp3  | 10.7 MB  |        98%        |      1 sec       |       2 sec        |     ✔️      |

