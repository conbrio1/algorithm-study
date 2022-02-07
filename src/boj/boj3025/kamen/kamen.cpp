
/*
  Croatian Open Competition in Informatics 2006/2007
  Contest 6 - Task KAMEN
  Programming language C++
*/

#include <cstdio>

using namespace std;

int R, S, N;
char a[30000][32];

struct putanja { // putanja == 경로, 길
   char stupac[30000]; // row수만큼 stupac(열)
   int r; // r은 row

   void ubaci() { // ubaci == insert
      a[r-1][stupac[r-1]] = 'O';
   }

   void sredi() {
      for( ;; ) {
         int s = stupac[r-1]; // s는 열?

         if( r > 1 && a[r-1][s] != '.' ) { --r; continue; } // .이 아니면 .이 나올 때까지 r -= 1

         if( r == R ) break;
         if( a[r][s] == 'X' ) break;
         if( a[r][s] == '.' ) {
            stupac[r++] = s; // // stupac[r] = s 하고 다음 행으로
         } else { // 'O'
            if( s > 0 && a[r][s-1] == '.' && a[r-1][s-1] == '.' ) { // 왼쪽 검사
               stupac[r++] = s-1; // stupac[r] = s - 1로 하고 다음 행으로
            } else if( s+1 < S && a[r][s+1] == '.' && a[r-1][s+1] == '.' ) { // 오른쪽 검사
               stupac[r++] = s+1; // stupac[r] = s + 1로 하고 다음 행으로
            } else {
               break;
            }
         }
      }
   }
} P[30]; // 해당 열에 대한 정보인듯(경로 인듯)

int main( void ) {
   scanf( "%d%d", &R, &S );
   for( int r = 0; r < R; ++r ) scanf( "%s", a[r] );

   for( int i = 0; i < S; ++i ) {
      P[i].stupac[0] = i; // 각 열의 경로의 열 첫번째 값은 해당 열과 동일
      P[i].r = 1; // 각 열의 경로의 행 첫번째 값은 1
      P[i].sredi(); // 초기 경로 생성
   }

   scanf( "%d", &N );
   for( int i = 0; i < N; ++i ) {
      int s;
      scanf( "%d", &s ); --s;
      P[s].ubaci();
      for( int i = 0; i < S; ++i ) P[i].sredi(); // 삽입할 때마다 모든 경로 update
   }

   for( int r = 0; r < R; ++r ) printf( "%s\n", a[r] );

   return 0;
}
