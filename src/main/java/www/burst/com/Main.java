package www.burst.com;

public class Main {
	public static void main(String args[]){
		//本番で解析するデータセットは以下の要素から成る．
		//合計到着間隔(gaps)
		//到着時間(arrt)
		//間隔個数(gapn)
		//平均到着間隔avg(h,t) = gaps / gapn
		
		//データセットの例を以下に定義する.(実際には平均到着間隔を格納)
		int[] Array = {80, 92, 45, 100, 2, 25}; //　解析データセット例を配列で定義.
		int N = 2; //レベル0のセル数(ピラミッドのサイズ)
		double B = 0.4; //0 < β < 1となる値(Burst判定係数)
		
		//AggregationPyramidをconstructorで初期化
		AggregationPyramid AP = new AggregationPyramid(N, Array, B);
		
		AP.Act();//実行呼び出し.
	}
}