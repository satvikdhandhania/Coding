


#include <stdio.h>
int main()
{
    FILE* cache_file = fopen("a","rb");
    FILE* file2 = fopen("b","ab");
    char buf[1000];
    size_t n;
    while ((n = fread(buf, 1, sizeof(buf), cache_file)) > 0) {
        fwrite(buf, 1, n, file2);
    }
    fclose(cache_file);
    fclose(file2);
}   
