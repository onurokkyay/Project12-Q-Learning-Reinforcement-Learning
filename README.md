
# Project12-Q-Learning-Reinforcement-Learning

## Pekiştirmeli öğrenme

Pekiştirmeli öğrenme (reinforcement learning), öznelerin (agent) bir görevi en yüksek
kazançla tamamlayabilmek için hangi eylemleri gerçekleştirmeleri gerektigi ile
ilgilenen bir makine öğrenmesi tekniğidir. Bu tür öğrenme algoritmaların girdisi
öznelerin görev yapacakları farklı durumlardan oluşan bir ortam S, yapabilecekleri
eylemler A, ortamdaki durumuna göre yapabilecekleri eylemleri belirleyen prensipler, bir
durumdan diğer duruma geçtiklerinde elde edecekleri kazançtır.
## Q-learning
Q-learning pekiştirmeli bir öğrenme algoritmasıdır. Ortam hakkında hiçbir şeyin
bilinmediği durumlarda, Q-learning algoritması ortamı brute-force şeklinde, her
ortam için olası tüm aksiyonları takip ederek, problem çözümü için en karlı yolu
bulmaya çalışır. 

## Labirent ve en kısa yol 
![labirent](https://user-images.githubusercontent.com/59628395/120541789-013a4f80-c3f3-11eb-843d-5c7adaad1df1.png)


## I. GİRİŞ
Q-learning pekiştirmeli bir öğrenme algoritmasıdır. Ortam hakkında hiçbir şeyin bilinmediği durumlarda, Q-learning algoritması ortamı brute-force şeklinde, her ortam için olası tüm aksiyonları takip ederek, problem çözümü için en karlı yolu bulmaya çalışır. Q-learning algoritmasının girdileri kazanç matrisi olarak adlandırılan R matrisidir. Bu matrisin satır ve sütunları ortamları temsil etmekte, R[i][j] değeri ise i durumundan j durumuna geçtiğinde elde edilen anlık kazanç değeridir. Eğer i durumunda j durumuna bir geçiş yoksa R[i][j] değeri -1, geçis var ancak j durumu hedef durum değilse değeri 0, j hedef durum ise değeri kullanıcı tarafından belirlenen bir kazanç değeridir. Q-learning algoritmasının çıktısı ise öğrenmenin kalitesini gösteren Q matrisidir. Q-learning iteratif bir algoritmadır ve tüm değerleri başlangıçta 0 olan Q matrisi optimal değerlere yakınsadığı da sona erer. Algoritma her iterasyonda rastgele bir durumdan öğrenmeye baslar, A’ya göre durum değiştirir ve Q matrisini günceller. A’ya göre hedef duruma ulaşıldığında iterasyon sona erer. A’ya göre bir durumdan birden fazla duruma geçis olabilir. Boyle bir durumda, olası geçişler den biri rastgele seçilir. Eğer seçilen durum hedef duruma ulaştırmıyorsa, durum rastgele olacak durum olarak belirlenir. Hedef duruma ulaşılanakadar iterasyon devam eder

1)	Harita ve engeller oluşturulur.

2) Haritaya ve engellere özgü matrisler oluşturulur.
3) Q ve R matrisleri oluşturulur.
4) Arayüzden kullanıcıdan başlangıç ve hedef noktaları istenir.
5) Start butonuna başlatılarak program başlatılır.
6) İşlemler bittikten sonra arayüzde en kısa yol gösterilir.
7) Grafikler kullanıcıya gösterilir. 
8)Txt dosyasına engeller yazdırılır.

## II.	YÖNTEM
1)	R matrisinde gidilebilen komşulara 0,gidilemeyenlere 
2)	-1 , hedefe de 100 verilir.
   2)  Q matrisi bütün elemanları 0 olacak şekilde yaratılır.
   3) Map matrisi yaratılır. %30 oranında rastgele engeller oluşturulur. Engellere -1 değeri verilir. Diğer elemanlar 0 değerini alır.
  4) Görsel arayüz oluşturulur.
  5) Kullanıcıdan başlangıç ve hedef bilgileri alınır.
 6) Bilgiler alındıktan sonra her bir adım çalışmaya başlar. Q matrisi aşağıdaki formüle göre güncellenir: Q(durum, aksiyon) = R(durum, aksiyon)+γ×Max{Q(sonraki durumlar, tüm aksiyonlar)} γ ogrenme katsayısıdır ve 0 ile 1 arasında bir değer alır.
7) İndirim faktörü γ = 0.9, kırmızıya çarparsa -5 ödül puanı,yeşil bitiş noktasına +5, diğer geçişlere +3 ödül puanı olarak hesaplanacaktır.
8) Q Matrisi doldurulduktan sonra en yüksek skorlu yollar izlenerek en kısa yol arayüzde çizdirilir.


## Episode via Step

![episodeviastep](https://user-images.githubusercontent.com/59628395/120541840-11eac580-c3f3-11eb-9bc3-08933f7d4e0a.png)
