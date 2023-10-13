package com.example.lib_edgelight.edgeLight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;

import com.example.lib_edgelight.utils.Const;
import com.example.lib_edgelight.utils.MySharePreferencesEdge;


public class EdgeBorderBorderLightAnimate implements IEdgeBorderLight {
    private static final String TAG = "EdgeLightAnimate";
    private float angle = 0.0f;
    private Bitmap bb;
    private Bitmap bitmap;
    private Bitmap bitmapShape;
    private int centerX;
    private int centerY;
    private boolean checkRotate = false;
    private int[] colors;
    private Context context;
    private float distance;
    private int height = 0;
    private int heigthInfility = 150;
    private int holeRadius = 60;
    private int holeRadiusY = 60;
    private int holeX = 200;
    private int holeY = 200;
    private String infility = Const.NO;
    private Bitmap mBitmap;
    private Matrix matrix;
    private boolean notch = false;
    private int notchBottom = 150;
    private int notchCenter = 200;
    private int notchHeight = 100;
    private int notchRadiusBottom = 200;
    private int notchRadiusTop = 200;
    private int notchTop = 200;
    private float padding = 35.0f;
    private Paint paint;
    private Path path;
    private PathMeasure pathMeasure;
    private float[] position;
    private float[] positions;
    float[] radii;
    private int radiusBInfility;
    private int radiusBottom;
    private int radiusInfility;
    private int radiusTop;
    private SweepGradient shader;
    private Shader shaderB;
    private String shape;
    private String sharp;
    private float[] slope;
    private float speed;
    private float strokeWidth;
    private int width;
    private int widthInfility;

    public EdgeBorderBorderLightAnimate(Context context2) {
        Paint paint2 = new Paint();
        this.paint = paint2;
        this.position = new float[2];
        this.radii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.radiusBInfility = 150;
        this.radiusBottom = 0;
        this.radiusInfility = 150;
        this.radiusTop = 0;
        this.shape = Const.LINE;
        this.sharp = Const.NO;
        this.slope = new float[2];
        this.speed = 2.0f;
        this.strokeWidth = 70.0f;
        this.width = 0;
        this.widthInfility = 100;
        paint2.setStrokeWidth(70.0f);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.MITER);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setAntiAlias(true);
        this.path = new Path();
        this.matrix = new Matrix();
        this.context = context2;
        int[] iArr = {-65536, -256, -16711936, Color.parseColor("#FF03A9F4"), -16776961, -65281, -65536};
        this.colors = iArr;
        this.distance = 1.0f / ((float) (iArr.length - 1));
        initPositions();
    }

    @Override 
    public void onLayout(int i, int i2) {
        this.width = i;
        this.height = i2;
        drawPathLine(i, i2, this.notch, this.sharp, this.infility);
        drawShapeInLine();
        this.shader = new SweepGradient((float) (i / 2), (float) (i2 / 2), this.colors, this.positions);
    }

    @Override 
    public void onDraw(Canvas canvas) {
        Shader shader2;
        float f = this.angle + this.speed;
        this.angle = f;
        this.matrix.setRotate(f, (float) (this.width / 2), (float) (this.height / 2));
        this.shader.setLocalMatrix(this.matrix);
        int i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.WIDTH, this.context);
        int i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.HEIGHT, this.context);
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 != null) {
            if (i > 0 && i2 > 0) {
                this.mBitmap = Bitmap.createScaledBitmap(bitmap2, i, i2, false);
            }
            canvas.save();
            canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
        }
        if (this.shape.equals(Const.LINE)) {
            this.paint.setShader(this.shader);
            this.paint.setStrokeWidth(this.strokeWidth);
            canvas.drawPath(this.path, this.paint);
        } else if (this.bitmapShape != null && (shader2 = this.shaderB) != null) {
            this.paint.setShader(new ComposeShader(this.shader, shader2, PorterDuff.Mode.DST_IN));
            canvas.drawPath(this.path, this.paint);
        }
    }

    @Override 
    public void changeColor(int[] iArr) {
        this.colors = iArr;
        initPositions();
        this.shader = new SweepGradient((float) (this.width / 2), (float) (this.height / 2), this.colors, this.positions);
    }

    @Override 
    public void setBitmap(Bitmap bitmap2) {
        this.mBitmap = bitmap2;
    }

    private void initPositions() {
        this.positions = new float[this.colors.length];
        int i = 0;
        while (true) {
            int[] iArr = this.colors;
            if (i < iArr.length) {
                if (i == 0) {
                    this.positions[0] = this.distance / 2.0f;
                } else if (i == iArr.length - 1) {
                    this.positions[iArr.length - 1] = 1.0f;
                } else {
                    float[] fArr = this.positions;
                    fArr[i] = fArr[i - 1] + this.distance;
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void drawPathLine(int i, int i2, boolean z, String str, String str2) {
        this.path.reset();
        Path path2 = this.path;
        float f = this.padding;
        path2.moveTo(((float) this.radiusTop) + f, f);
        drawLed(i, i2, z, str2);
        if (!this.checkRotate) {
            if (str.equals(Const.CIRCLE)) {
                this.path.addCircle((float) this.holeX, (float) this.holeY, (float) this.holeRadius, Path.Direction.CW);
            } else if (str.equals(Const.ROUND)) {
                Path path3 = this.path;
                int i3 = this.holeX;
                int i4 = this.holeRadius;
                int i5 = this.holeY;
                int i6 = this.holeRadiusY;
                path3.addRoundRect(new RectF((float) (i3 - i4), (float) (i5 - i6), (float) (i3 + i4), (float) (i5 + i6)), this.radii, Path.Direction.CW);
            }
        } else if (str.equals(Const.CIRCLE)) {
            this.path.addCircle((float) (i - this.holeY), (float) this.holeX, (float) this.holeRadius, Path.Direction.CW);
        } else if (str.equals(Const.ROUND)) {
            Path path4 = this.path;
            int i7 = this.holeY;
            int i8 = this.holeRadiusY;
            int i9 = this.holeX;
            int i10 = this.holeRadius;
            int i11 = i - i7;
            path4.addRoundRect(new RectF((float) (i11 - i8), (float) (i9 - i10), (float) (i11 + i8), (float) (i9 + i10)), this.radii, Path.Direction.CW);
        }
        this.path.close();
        this.pathMeasure = new PathMeasure(this.path, false);
    }

    private void drawShapeInLine() {
        int i;
        int i2 = this.width;
        if (i2 > 0 && (i = this.height) > 0 && this.bb != null) {
            if (this.pathMeasure != null) {
                this.bitmapShape = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(this.bitmapShape);
                int length = (int) (this.pathMeasure.getLength() / ((float) this.bb.getWidth()));
                int i3 = 1;
                for (int i4 = 0; i4 < length; i4++) {
                    this.pathMeasure.getPosTan((float) i3, this.position, this.slope);
                    canvas.save();
                    float[] fArr = this.position;
                    canvas.translate(fArr[0] - ((float) this.centerX), fArr[1] - ((float) this.centerY));
                    canvas.drawBitmap(this.bb, 0.0f, 0.0f, (Paint) null);
                    canvas.restore();
                    i3 += this.bb.getWidth();
                }
                if (this.sharp.equals(Const.CIRCLE) || this.sharp.equals(Const.ROUND)) {
                    Path path2 = new Path();
                    path2.reset();
                    if (!this.checkRotate) {
                        if (this.sharp.equals(Const.CIRCLE)) {
                            path2.addCircle((float) this.holeX, (float) this.holeY, (float) this.holeRadius, Path.Direction.CW);
                        } else if (this.sharp.equals(Const.ROUND)) {
                            int i5 = this.holeX;
                            int i6 = this.holeRadius;
                            int i7 = this.holeY;
                            int i8 = this.holeRadiusY;
                            path2.addRoundRect(new RectF((float) (i5 - i6), (float) (i7 - i8), (float) (i5 + i6), (float) (i7 + i8)), this.radii, Path.Direction.CW);
                        }
                    } else if (this.sharp.equals(Const.CIRCLE)) {
                        path2.addCircle((float) (this.width - this.holeY), (float) this.holeX, (float) this.holeRadius, Path.Direction.CW);
                    } else if (this.sharp.equals(Const.ROUND)) {
                        int i9 = this.width;
                        int i10 = this.holeY;
                        int i11 = this.holeRadiusY;
                        int i12 = this.holeX;
                        int i13 = this.holeRadius;
                        int i14 = i9 - i10;
                        path2.addRoundRect(new RectF((float) (i14 - i11), (float) (i12 - i13), (float) (i14 + i11), (float) (i12 + i13)), this.radii, Path.Direction.CW);
                    }
                    path2.close();
                    PathMeasure pathMeasure2 = new PathMeasure(path2, false);
                    int length2 = (int) (pathMeasure2.getLength() / ((float) this.bb.getWidth()));
                    int i15 = 1;
                    for (int i16 = 0; i16 < length2; i16++) {
                        pathMeasure2.getPosTan((float) i15, this.position, this.slope);
                        canvas.save();
                        float[] fArr2 = this.position;
                        canvas.translate(fArr2[0] - ((float) this.centerX), fArr2[1] - ((float) this.centerY));
                        canvas.drawBitmap(this.bb, 0.0f, 0.0f, (Paint) null);
                        canvas.restore();
                        i15 += this.bb.getWidth();
                    }
                }
            }
            Bitmap bitmap2 = this.bitmapShape;
            if (bitmap2 != null) {
                this.shaderB = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
        }
    }

    private void drawLed(int i, int i2, boolean z, String str) {
        if (!this.checkRotate) {
            if (z) {
                int i3 = i / 2;
                this.path.lineTo((float) ((i3 - this.notchTop) - this.notchRadiusTop), this.padding);
                Path path2 = this.path;
                int i4 = this.notchTop;
                float f = this.padding;
                int i5 = i3 - i4;
                path2.cubicTo((float) (i5 - this.notchRadiusTop), f, (float) i5, f, (float) (i3 - this.notchCenter), f + ((float) (this.notchHeight / 2)));
                float f2 = this.padding;
                int i6 = this.notchHeight;
                float f3 = f2 + ((float) i6);
                this.path.cubicTo((float) (i3 - this.notchCenter), ((float) (i6 / 2)) + f2, (float) (i3 - this.notchBottom), f3, (float) (i3 - this.notchRadiusBottom), f3);
                this.path.lineTo((float) (this.notchRadiusBottom + i3), this.padding + ((float) this.notchHeight));
                Path path3 = this.path;
                float f4 = (float) (this.notchRadiusBottom + i3);
                float f5 = this.padding;
                int i7 = this.notchHeight;
                float f6 = f5 + ((float) i7);
                path3.cubicTo(f4, f6, (float) (this.notchBottom + i3), f6, (float) (this.notchCenter + i3), f5 + ((float) (i7 / 2)));
                float f7 = this.padding;
                float f8 = f7 + ((float) (this.notchHeight / 2));
                int i8 = i3 + this.notchTop;
                this.path.cubicTo((float) (this.notchCenter + i3), f8, (float) i8, f7, (float) (i8 + this.notchRadiusTop), f7);
                Path path4 = this.path;
                float f9 = this.padding;
                path4.lineTo(((float) (i - this.radiusTop)) - f9, f9);
            } else if (str.equals(Const.INFILITYV)) {
                int i9 = i / 2;
                this.path.lineTo((float) ((i9 - this.widthInfility) - this.radiusInfility), this.padding);
                Path path5 = this.path;
                int i10 = this.widthInfility;
                int i11 = this.radiusInfility;
                float f10 = this.padding;
                int i12 = i9 - i10;
                path5.cubicTo((float) (i12 - i11), f10, (float) i12, f10, (float) (i9 - (i10 / 2)), ((float) i11) + f10 + ((float) (this.heigthInfility / 2)));
                Path path6 = this.path;
                int i13 = this.widthInfility;
                float f11 = this.padding;
                int i14 = this.radiusInfility;
                int i15 = this.heigthInfility;
                int i16 = i13 / 2;
                float f12 = ((float) i14) + f11;
                float f13 = f12 + ((float) (i15 / 2));
                path6.cubicTo((float) (i9 - i16), f13, (float) i9, f12 + ((float) this.radiusBInfility) + ((float) i15), (float) (i16 + i9), f13);
                Path path7 = this.path;
                int i17 = this.widthInfility;
                float f14 = this.padding;
                int i18 = this.radiusInfility;
                int i19 = i9 + i17;
                path7.cubicTo((float) ((i17 / 2) + i9), ((float) i18) + f14 + ((float) (this.heigthInfility / 2)), (float) i19, f14, (float) (i19 + i18), f14);
                Path path8 = this.path;
                float f15 = this.padding;
                path8.lineTo(((float) (i - this.radiusTop)) - f15, f15);
            } else if (str.equals(Const.INFILITYU)) {
                int i20 = i / 2;
                this.path.lineTo((float) ((i20 - this.widthInfility) - this.radiusInfility), this.padding);
                Path path9 = this.path;
                int i21 = this.widthInfility;
                int i22 = this.radiusInfility;
                float f16 = this.padding;
                int i23 = i20 - i21;
                float f17 = (float) i23;
                path9.cubicTo((float) (i23 - i22), f16, f17, f16, f17, f16 + ((float) i22));
                this.path.lineTo((float) (i20 - this.widthInfility), this.padding + ((float) this.radiusInfility) + ((float) this.heigthInfility));
                Path path10 = this.path;
                int i24 = this.widthInfility;
                float f18 = this.padding;
                float f19 = (float) i20;
                float f20 = (float) (i20 - i24);
                float f21 = (float) this.radiusInfility;
                float f22 = (float) this.heigthInfility;
                float f23 = f21 + f18 + f22;
                float f24 = f22 + f18 + f21 + 100.0f;
                path10.cubicTo(f20, f23, f20, f24, f19, f24);
                Path path11 = this.path;
                float f25 = this.padding;
                int i25 = this.heigthInfility;
                float f26 = (float) i25;
                float f27 = (float) this.radiusInfility;
                float f28 = f26 + f25 + f27 + 100.0f;
                float f29 = (float) (this.widthInfility + i20);
                path11.cubicTo(f19, f28, f29, f28, f29, f25 + f27 + f26);
                this.path.lineTo((float) (this.widthInfility + i20), this.padding + ((float) this.radiusInfility));
                Path path12 = this.path;
                int i26 = this.widthInfility;
                float f30 = this.padding;
                int i27 = this.radiusInfility;
                int i28 = i20 + i26;
                float f31 = (float) i28;
                path12.cubicTo(f31, f30 + ((float) i27), f31, f30, (float) (i28 + i27), f30);
                Path path13 = this.path;
                float f32 = this.padding;
                path13.lineTo(((float) (i - this.radiusTop)) - f32, f32);
            } else {
                Path path14 = this.path;
                float f33 = this.padding;
                path14.lineTo(((float) (i - this.radiusTop)) - f33, f33);
            }
            Path path15 = this.path;
            int i29 = this.radiusTop;
            float f34 = this.padding;
            float f35 = (float) i;
            float f36 = f35 - f34;
            path15.cubicTo(((float) (i - i29)) - f34, f34, f36, f34, f36, ((float) i29) + f34);
            Path path16 = this.path;
            float f37 = this.padding;
            float f38 = (float) i2;
            path16.lineTo(f35 - f37, (f38 - f37) - ((float) this.radiusBottom));
            Path path17 = this.path;
            float f39 = this.padding;
            float f40 = f35 - f39;
            float f41 = f38 - f39;
            float f42 = (float) this.radiusBottom;
            path17.cubicTo(f40, f41 - f42, f40, f41, f40 - f42, f41);
            Path path18 = this.path;
            float f43 = this.padding;
            path18.lineTo(((float) this.radiusBottom) + f43, f38 - f43);
            Path path19 = this.path;
            float f44 = this.padding;
            float f45 = (float) this.radiusBottom;
            float f46 = f38 - f44;
            path19.cubicTo(f44 + f45, f46, f44, f46, f44, f46 - f45);
            Path path20 = this.path;
            float f47 = this.padding;
            path20.lineTo(f47, ((float) this.radiusTop) + f47);
            Path path21 = this.path;
            float f48 = this.padding;
            float f49 = f48 + ((float) this.radiusTop);
            path21.cubicTo(f48, f49, f48, f48, f49, f48);
            Path path22 = this.path;
            float f50 = this.padding;
            path22.lineTo(((float) this.radiusTop) + f50 + 20.0f, f50);
            return;
        }
        Path path23 = this.path;
        float f51 = this.padding;
        path23.lineTo(((float) (i - this.radiusTop)) - f51, f51);
        Path path24 = this.path;
        int i30 = this.radiusTop;
        float f52 = this.padding;
        float f53 = (float) i;
        float f54 = f53 - f52;
        path24.cubicTo(((float) (i - i30)) - f52, f52, f54, f52, f54, ((float) i30) + f52);
        if (z) {
            int i31 = i2 / 2;
            this.path.lineTo(f53 - this.padding, (float) ((i31 - this.notchTop) - this.notchRadiusTop));
            Path path25 = this.path;
            float f55 = f53 - this.padding;
            int i32 = i31 - this.notchTop;
            path25.cubicTo(f55, (float) (i32 - this.notchRadiusTop), f55, (float) i32, f55 - ((float) (this.notchHeight / 2)), (float) (i31 - this.notchCenter));
            Path path26 = this.path;
            float f56 = this.padding;
            int i33 = this.notchHeight;
            float f57 = f53 - f56;
            float f58 = f57 - ((float) i33);
            path26.cubicTo(f57 - ((float) (i33 / 2)), (float) (i31 - this.notchCenter), f58, (float) (i31 - this.notchBottom), f58, (float) (i31 - this.notchRadiusBottom));
            this.path.lineTo((f53 - this.padding) - ((float) this.notchHeight), (float) (this.notchRadiusBottom + i31));
            Path path27 = this.path;
            float f59 = this.padding;
            int i34 = this.notchHeight;
            float f60 = f53 - f59;
            float f61 = f60 - ((float) i34);
            path27.cubicTo(f61, (float) (this.notchRadiusBottom + i31), f61, (float) (this.notchBottom + i31), f60 - ((float) (i34 / 2)), (float) (this.notchCenter + i31));
            Path path28 = this.path;
            float f62 = this.padding;
            float f63 = f53 - f62;
            int i35 = i31 + this.notchTop;
            path28.cubicTo(f63 - ((float) (this.notchHeight / 2)), (float) (this.notchCenter + i31), f63, (float) i35, f63, (float) (i35 + this.notchRadiusTop));
            Path path29 = this.path;
            float f64 = this.padding;
            path29.lineTo(f53 - f64, (((float) i2) - f64) - ((float) this.radiusBottom));
        } else if (str.equals(Const.INFILITYV)) {
            int i36 = i2 / 2;
            this.path.lineTo(f53 - this.padding, (float) ((i36 - this.widthInfility) - this.radiusInfility));
            Path path30 = this.path;
            float f65 = this.padding;
            int i37 = this.widthInfility;
            int i38 = this.radiusInfility;
            float f66 = f53 - f65;
            int i39 = i36 - i37;
            path30.cubicTo(f66, (float) (i39 - i38), f66, (float) i39, (f66 - ((float) i38)) - ((float) (this.heigthInfility / 2)), (float) (i36 - (i37 / 2)));
            Path path31 = this.path;
            float f67 = this.padding;
            int i40 = this.radiusInfility;
            int i41 = this.heigthInfility;
            float f68 = (f53 - f67) - ((float) i40);
            float f69 = f68 - ((float) (i41 / 2));
            int i42 = this.widthInfility / 2;
            path31.cubicTo(f69, (float) (i36 - i42), (f68 - ((float) this.radiusBInfility)) - ((float) i41), (float) i36, f69, (float) (i42 + i36));
            Path path32 = this.path;
            float f70 = this.padding;
            int i43 = this.radiusInfility;
            float f71 = f53 - f70;
            float f72 = (f71 - ((float) i43)) - ((float) (this.heigthInfility / 2));
            int i44 = this.widthInfility;
            int i45 = i36 + i44;
            path32.cubicTo(f72, (float) ((i44 / 2) + i36), f71, (float) i45, f71, (float) (i45 + i43));
            Path path33 = this.path;
            float f73 = this.padding;
            path33.lineTo(f53 - f73, (((float) i2) - f73) - ((float) this.radiusBottom));
        } else if (str.equals(Const.INFILITYU)) {
            int i46 = i2 / 2;
            this.path.lineTo(f53 - this.padding, (float) ((i46 - this.widthInfility) - this.radiusInfility));
            Path path34 = this.path;
            float f74 = this.padding;
            int i47 = this.widthInfility;
            int i48 = this.radiusInfility;
            float f75 = f53 - f74;
            int i49 = i46 - i47;
            float f76 = (float) i49;
            path34.cubicTo(f75, (float) (i49 - i48), f75, f76, f75 - ((float) i48), f76);
            this.path.lineTo(((f53 - this.padding) - ((float) this.radiusInfility)) - ((float) this.heigthInfility), (float) (i46 - this.widthInfility));
            Path path35 = this.path;
            float f77 = this.padding;
            int i50 = this.radiusInfility;
            float f78 = (float) i46;
            float f79 = f53 - f77;
            float f80 = (float) i50;
            float f81 = (float) this.heigthInfility;
            float f82 = (float) (i46 - this.widthInfility);
            float f83 = ((f79 - f81) - f80) - 100.0f;
            path35.cubicTo((f79 - f80) - f81, f82, f83, f82, f83, f78);
            Path path36 = this.path;
            float f84 = this.padding;
            int i51 = this.heigthInfility;
            float f85 = f53 - f84;
            float f86 = (float) i51;
            float f87 = (float) this.radiusInfility;
            float f88 = ((f85 - f86) - f87) - 100.0f;
            float f89 = (float) (this.widthInfility + i46);
            path36.cubicTo(f88, f78, f88, f89, (f85 - f87) - f86, f89);
            this.path.lineTo((f53 - this.padding) - ((float) this.radiusInfility), (float) (this.widthInfility + i46));
            Path path37 = this.path;
            float f90 = this.padding;
            int i52 = this.radiusInfility;
            float f91 = f53 - f90;
            int i53 = i46 + this.widthInfility;
            float f92 = (float) i53;
            path37.cubicTo(f91 - ((float) i52), f92, f91, f92, f91, (float) (i53 + i52));
            Path path38 = this.path;
            float f93 = this.padding;
            path38.lineTo(f53 - f93, (((float) i2) - f93) - ((float) this.radiusBottom));
        } else {
            Path path39 = this.path;
            float f94 = this.padding;
            path39.lineTo(f53 - f94, (((float) i2) - f94) - ((float) this.radiusBottom));
        }
        Path path40 = this.path;
        float f95 = this.padding;
        float f96 = (float) i2;
        float f97 = f53 - f95;
        float f98 = f96 - f95;
        float f99 = (float) this.radiusBottom;
        path40.cubicTo(f97, f98 - f99, f97, f98, f97 - f99, f98);
        Path path41 = this.path;
        float f100 = this.padding;
        path41.lineTo(((float) this.radiusBottom) + f100, f96 - f100);
        Path path42 = this.path;
        float f101 = this.padding;
        float f102 = (float) this.radiusBottom;
        float f103 = f96 - f101;
        path42.cubicTo(f101 + f102, f103, f101, f103, f101, f103 - f102);
        Path path43 = this.path;
        float f104 = this.padding;
        path43.lineTo(f104, ((float) this.radiusTop) + f104);
        Path path44 = this.path;
        float f105 = this.padding;
        float f106 = f105 + ((float) this.radiusTop);
        path44.cubicTo(f105, f106, f105, f105, f106, f105);
        Path path45 = this.path;
        float f107 = this.padding;
        path45.lineTo(((float) this.radiusTop) + f107 + 20.0f, f107);
    }

    public void changeSpeed(float f) {
        this.speed = f;
        this.matrix.setRotate(this.angle + f, (float) (this.width / 2), (float) (this.height / 2));
        this.shader.setLocalMatrix(this.matrix);
    }

    public void changeSize(int i) {
        Bitmap bitmap2;
        float f = (float) i;
        this.strokeWidth = f;
        this.padding = (float) (i / 2);
        this.paint.setStrokeWidth(f);
        drawPathLine(this.width, this.height, this.notch, this.sharp, this.infility);
        if (i > 0 && (bitmap2 = this.bitmap) != null) {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, i, i, false);
            this.bb = createScaledBitmap;
            this.centerX = createScaledBitmap.getWidth() / 2;
            this.centerY = this.bb.getHeight() / 2;
        }
        drawShapeInLine();
    }

    public void changeRadius(int i, int i2) {
        this.radiusTop = i;
        this.radiusBottom = i2;
        drawPathLine(this.width, this.height, this.notch, this.sharp, this.infility);
        drawShapeInLine();
    }

    public void changeNotch(boolean z, int i, int i2, int i3, int i4, int i5) {
        this.notchTop = i;
        this.notchRadiusTop = i4;
        this.notchRadiusBottom = i5;
        this.notchCenter = (i + i2) / 2;
        this.notchBottom = i2;
        this.notchHeight = i3;
        this.notch = z;
        drawPathLine(this.width, this.height, z, this.sharp, this.infility);
        drawShapeInLine();
    }

    public void changeHole(String str, int i, int i2, int i3, int i4, int i5) {
        float[] fArr = this.radii;
        float f = (float) i5;
        fArr[0] = f;
        fArr[2] = f;
        fArr[3] = f;
        fArr[1] = f;
        fArr[4] = f;
        fArr[5] = f;
        fArr[6] = f;
        fArr[7] = f;
        this.sharp = str;
        this.holeRadius = i3;
        this.holeX = i;
        this.holeY = i2;
        this.holeRadiusY = i4;
        drawPathLine(this.width, this.height, this.notch, str, this.infility);
        drawShapeInLine();
    }

    public void changeInfility(String str, int i, int i2, int i3, int i4) {
        this.infility = str;
        this.widthInfility = i;
        this.heigthInfility = i2;
        this.radiusInfility = i3;
        this.radiusBInfility = i4;
        drawPathLine(this.width, this.height, this.notch, this.sharp, str);
        drawShapeInLine();
    }

    public void changeShape(String str, Bitmap bitmap2) {
        this.shape = str;
        this.bitmap = bitmap2;
        if (this.paint.getStrokeWidth() > 0.0f && bitmap2 != null) {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, (int) this.paint.getStrokeWidth(), (int) this.paint.getStrokeWidth(), false);
            this.bb = createScaledBitmap;
            this.centerX = createScaledBitmap.getWidth() / 2;
            this.centerY = this.bb.getHeight() / 2;
        }
        drawPathLine(this.width, this.height, this.notch, this.sharp, this.infility);
        drawShapeInLine();
    }

    public void changeRotate(boolean z) {
        this.checkRotate = z;
    }
}
