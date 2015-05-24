package www.burst.com;

public class AggregationPyramid {
	private int N; //セルの数
	private int[] Array; //データ
	private double B; //β倍(バースト判定係数)
	//コンストラクタ
	public AggregationPyramid(int Num, int[] Array, double B){
		this.N = Num;
		this.Array = Array;
		this.B = B;
	}
	public AggregationPyramid(int Num, double B, int[] Array){
		this.N = Num;
		this.Array = Array;
		this.B = B;
	}
	public AggregationPyramid(int[] Array,int Num, double B){
		this.N = Num;
		this.Array = Array;
		this.B = B;
	}
	public AggregationPyramid(int[] Array, double B,int Num){
		this.N = Num;
		this.Array = Array;
		this.B = B;
	}
	public AggregationPyramid(double B,int Num,int[] Array){
		this.N = Num;
		this.Array = Array;
		this.B = B;
	}
	public AggregationPyramid(double B,int[] Array,int Num){
		this.N = Num;
		this.Array = Array;
		this.B = B;
	}
	
	public void Act(){
		int N = this.N;//セルの個数の設定
		double B = this.B;//β倍の設定
		int data[][] = new int[N][N];//データ格納用
		int arr = this.Array.length;//配列型のデータ数
		int Burst[] = new int[arr - N + 1];//Burst判定に使用する配列
		int act_num = (arr - N) + 1;//実行回数
		System.out.println("データセット総数: " + arr);//配列数の確認
		System.out.println("判定回数: " + (act_num - 1));//判定回数の確認
		
		if(N - arr < 0){
			//レベル0のセル数Nよりデータ数が多い場合
			//ピラミッドのサイズ以上にデータが存在している場合
			//つまり，解析可能である場合
			for (int act = 0; act < act_num; act++){
				/*以下のfor文でピラミッドのサイズにあわせてデータを格納*/
				for (int i = 0; i < N; i++){
					data[0][i]= Array[act + i];
				}
					
				//AggregationPyramidの最上部(N-1)の値を格納
				Burst[act] = this.pyramid(data, N);
				//実行回数が2回以上ないとバースト判定できないため(0からfor文が始めっているため、これで良い)
				if(act >= 1){
					//threshold
					double threshold = (B * Burst[act - 1] - Burst[act]);//β倍したBurst判定結果がどうであるのか確認(後<前*β 0以上であればBurst)
					
					if(threshold >= 0){
						//バースト発生
						System.out.println(act + "回目の判定結果");
						System.out.println("Burst発生!!!");						
						System.out.println(threshold);

					}else{
						//バースト未発生
						System.out.println(act + "回目の判定結果");
						System.out.println(threshold);
					}
				}
			}		
		}else if(N - arr == 0){
			//レベル0のセル数Nとデータ数が一致した場合
			//バースト判定できない
			for (int i = 0; i < arr; i++){
				data[0][i]= Array[i];
			}
			int result = this.pyramid(data, N);
			System.out.println("C[" + (N - 1) + "][0]の計算結果:"+ result);
			
		}else{
			//レベル0のセル数Nよりもデータ数が少ない場合(error)
			System.out.println("An error has occurred! ");
			System.out.println("Data is not enough! ");
		}
	}
	
	//最大レベルのセル(N-1)を計算するMethod.
	public int pyramid(int[][] data, int N){
		//レベル(N-1)までのピラミッドを計算する
		int less = N;
		int C[][] = data;
		for (int i = 0; i < N; i++){
			//System.out.println("レベル"+ i + "のセル");
			for (int j = 0; j < less; j++){
				if(i == 0){
					//レベル0のセル
					//System.out.println(C[i][j]);
					//レベル0のセルは当然計算しないで良い
				}else{
					//レベル1以上のセルの計算
					C[i][j] = C[i-1][j] + C[i-1][j+1];
					//System.out.println(C[i][j]);
				}
			}
			less -= 1;//1ずつ減算
		}
		//計算結果
	    return C[N-1][0];
	}
}