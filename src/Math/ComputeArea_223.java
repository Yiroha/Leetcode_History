package Math;

public class ComputeArea_223 {
    /*223. 矩形面积*/
    /*
    分情况讨论，分别计算长和宽重叠的4种情况;
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int s1 = (C - A) * (D - B);
        int s2 = (G - E) * (H - F);
        int l = 0, w = 0;
        if(C >= G && A >= E && G >= A){
            w = G - A;
        }
        if(C >= E && E >= A && G >= C){
            w = C - E;
        }
        if(G > C && A > E){
            w = C - A;
        }
        if(C > G && E > A){
            w = G - E;
        }
        if(H >= D && D >= F && F >= B){
            l = D - F;
        }
        if(D >= H && H >= B && B >= F){
            l = H - B;
        }
        if(H > D && B > F){
            l = D - B;
        }
        if(D > H && F > B){
            l = H -F;
        }
        return s1 + s2 - l * w;
    }
}
