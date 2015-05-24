package www.cosine.similarity.com;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

//マップ(Map)の使い方
//２つの文書ベクトルの類似度として余弦を用いる．

public class CosineSample {
	public static void main(String[] args) {
		//マップ・オブジェクトの生成
				Map<String,Integer> mapA = new HashMap<String,Integer>();
				Map<String,Integer> mapB = new HashMap<String,Integer>();

				//文書に含まれる単語とその出現回数を特徴とするとき，
				//特徴ベクトルは「単語の出現回数の並び」から成る．
				//しかし，数学的に同一次元で表現しようとすると，
				//ほとんどの成分がゼロとなり無駄が多い．
				//そこで，マップで実装した上で，計算方法を工夫する．
				
				//各マップに要素を追加
				//(注意) Map(key, value) の key は一意でなければならない．
				mapA.put("ABC", 1); mapA.put("DEF", 2); mapA.put("GHI", 3);
				mapB.put("ABC", 3); mapB.put("DEF", 2); mapB.put("JKL", 1);

				//各マップの要素数を出力
				System.out.println("size of mapA:" + mapA.size());	// 3が出力される
				System.out.println("size of mapB:" + mapB.size());	// 3が出力される

				//マップから key 集合を取り出すには Map.keySet() を用いる．
				Set<String> set = mapA.keySet();
				Iterator<String> iter = set.iterator();
				while(iter.hasNext()){
					String key = iter.next();
					//マップ中の key に対応する value を取り出すには Map.get() を用いる
					System.out.println(key + ":" + mapA.get(key));
				}

				//以下では２つのベクトル（＝マップ）を比較し，余弦に基づく類似度を求める．
				
				//最初に内積を求める
				Set<String> setA = mapA.keySet();
				Iterator<String> iterA = setA.iterator();
				double naiseki = 0.0;
				while(iterA.hasNext()){
					String key = iterA.next();
					if(mapB.containsKey(key)){
						naiseki += (double)(mapA.get(key) * mapB.get(key));
					}
				}
				System.out.println(naiseki);	// 1*3 + 2*2 = 7.0 が出力されるはず(内積)
				
				//次のベクトル Aのサイズを求める
				iterA = setA.iterator();
				double sizeA = 0.0;
				while(iterA.hasNext()){
					String key = iterA.next();
					sizeA += (double)(mapA.get(key) * mapA.get(key));
				}
				sizeA = Math.sqrt(sizeA);
				
				//次のベクトルBのサイズを求める
				Iterator<String> iterB = mapB.keySet().iterator(); 
				double sizeB = 0.0;
				while(iterB.hasNext()){
					String key = iterB.next();
					sizeB += (double)(mapB.get(key) * mapB.get(key));
				}
				sizeB = Math.sqrt(sizeB);

				//確認のため，それぞれのベクトルのサイズを出力する
				System.out.println("sizeA = " + sizeA + " , sizeB = " + sizeB); //sqrt(14)~3.74 が表示されるはず

				//最後にベクトル A とベクトル B の余弦を求める
				double cosine = naiseki / (sizeA * sizeB);	// 7 / (sqrt(14) * sqrt(14)) = 7 /14 = 0.5
				System.out.println("Cosine = " + cosine);
	}
}
