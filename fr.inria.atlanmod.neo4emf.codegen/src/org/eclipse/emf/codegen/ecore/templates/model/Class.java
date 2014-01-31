package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import fr.inria.atlanmod.neo4emf.codegen.CodegenUtil;

public class Class
{
  protected static String nl;
  public static synchronized Class create(String lineSeparator)
  {
    nl = lineSeparator;
    Class result = new Class();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */";
  protected final String TEXT_5 = NL + "package ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL + "package ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_12 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_13 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_14 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_15 = NL + " *";
  protected final String TEXT_16 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * <ul>";
  protected final String TEXT_17 = NL + " *   <li>{@link ";
  protected final String TEXT_18 = "#";
  protected final String TEXT_19 = " <em>";
  protected final String TEXT_20 = "</em>}</li>";
  protected final String TEXT_21 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_22 = NL + " *";
  protected final String TEXT_23 = NL + " * @see ";
  protected final String TEXT_24 = "#get";
  protected final String TEXT_25 = "()";
  protected final String TEXT_26 = NL + " * @model ";
  protected final String TEXT_27 = NL + " *        ";
  protected final String TEXT_28 = NL + " * @model";
  protected final String TEXT_29 = NL + " * @extends ";
  protected final String TEXT_30 = NL + " * @generated" + NL + " */";
  protected final String TEXT_31 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_32 = "</b></em>'." + NL + " * <!-- end-user-doc -->" + NL + " * <p>";
  protected final String TEXT_33 = NL + " * The following features are implemented:" + NL + " * <ul>";
  protected final String TEXT_34 = NL + " *   <li>{@link ";
  protected final String TEXT_35 = "#";
  protected final String TEXT_36 = " <em>";
  protected final String TEXT_37 = "</em>}</li>";
  protected final String TEXT_38 = NL + " * </ul>";
  protected final String TEXT_39 = NL + " * </p>" + NL + " *" + NL + " * @generated" + NL + " */";
  protected final String TEXT_40 = NL + "public";
  protected final String TEXT_41 = " abstract";
  protected final String TEXT_42 = " class ";
  protected final String TEXT_43 = NL + "public interface ";
  protected final String TEXT_44 = NL + "{";
  protected final String TEXT_45 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_46 = " copyright = ";
  protected final String TEXT_47 = ";";
  protected final String TEXT_48 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_49 = " mofDriverNumber = \"";
  protected final String TEXT_50 = "\";";
  protected final String TEXT_51 = NL;
  protected final String TEXT_52 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_53 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_54 = NL + "\t@";
  protected final String TEXT_55 = NL + "\tprotected Object[] ";
  protected final String TEXT_56 = ";" + NL;
  protected final String TEXT_57 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_58 = NL + "\t@";
  protected final String TEXT_59 = NL + "\tprotected int ";
  protected final String TEXT_60 = ";" + NL;
  protected final String TEXT_61 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_62 = NL + "\t@";
  protected final String TEXT_63 = NL + "\tprotected int ";
  protected final String TEXT_64 = " = 0;" + NL;
  protected final String TEXT_65 = NL;
  protected final String TEXT_66 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_67 = " = ";
  protected final String TEXT_68 = ".getFeatureID(";
  protected final String TEXT_69 = ") - ";
  protected final String TEXT_70 = ";" + NL;
  protected final String TEXT_71 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_72 = " = ";
  protected final String TEXT_73 = ".getFeatureID(";
  protected final String TEXT_74 = ") - ";
  protected final String TEXT_75 = ";" + NL;
  protected final String TEXT_76 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_77 = ".getOperationID(";
  protected final String TEXT_78 = ") - ";
  protected final String TEXT_79 = ";" + NL;
  protected final String TEXT_80 = NL + "\t " + NL + "\t";
  protected final String TEXT_81 = NL + "\t/**" + NL + "\t * The cached value of the data structure {@link Data";
  protected final String TEXT_82 = " <em>data</em> } " + NL + "\t * @generated" + NL + "\t */" + NL + "\t \tprotected Data";
  protected final String TEXT_83 = " data;" + NL + "\t ";
  protected final String TEXT_84 = NL + "\t " + NL + "\t " + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_85 = "public";
  protected final String TEXT_86 = "protected";
  protected final String TEXT_87 = " ";
  protected final String TEXT_88 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t\t";
  protected final String TEXT_89 = NL + "\t\t";
  protected final String TEXT_90 = " |= ";
  protected final String TEXT_91 = "_EFLAG";
  protected final String TEXT_92 = "_DEFAULT";
  protected final String TEXT_93 = ";";
  protected final String TEXT_94 = NL + "\t}" + NL + "\t";
  protected final String TEXT_95 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t ";
  protected final String TEXT_96 = "@Override";
  protected final String TEXT_97 = NL + "\tprotected Data";
  protected final String TEXT_98 = " getData(){" + NL + "\t\tif ( data == null || !(data instanceof Data";
  protected final String TEXT_99 = ")){" + NL + "\t\t\tdata = new Data";
  protected final String TEXT_100 = "();" + NL + "\t\t\tif (isLoaded())" + NL + "\t\t\t((";
  protected final String TEXT_101 = ") this.eResource()).fetchAttributes(this);" + NL + "\t\t\t}" + NL + "\t\treturn (Data";
  protected final String TEXT_102 = ") data;" + NL + "\t}" + NL + "\t";
  protected final String TEXT_103 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_104 = NL + "\t@Override";
  protected final String TEXT_105 = NL + "\tprotected ";
  protected final String TEXT_106 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_107 = ";" + NL + "\t}" + NL;
  protected final String TEXT_108 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_109 = NL + "\t@Override";
  protected final String TEXT_110 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_111 = ";" + NL + "\t}" + NL;
  protected final String TEXT_112 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX1" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_113 = NL + "\t";
  protected final String TEXT_114 = "[] ";
  protected final String TEXT_115 = "();" + NL;
  protected final String TEXT_116 = NL + "\tpublic ";
  protected final String TEXT_117 = "[] ";
  protected final String TEXT_118 = "()" + NL + "\t{";
  protected final String TEXT_119 = NL + "\t\t";
  protected final String TEXT_120 = " list = (";
  protected final String TEXT_121 = ")";
  protected final String TEXT_122 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_123 = "(";
  protected final String TEXT_124 = "[])";
  protected final String TEXT_125 = "_EEMPTY_ARRAY;";
  protected final String TEXT_126 = NL + "\t\tif (";
  protected final String TEXT_127 = " == null || ";
  protected final String TEXT_128 = ".isEmpty()) return ";
  protected final String TEXT_129 = "(";
  protected final String TEXT_130 = "[])";
  protected final String TEXT_131 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_132 = " list = (";
  protected final String TEXT_133 = ")";
  protected final String TEXT_134 = ";";
  protected final String TEXT_135 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_136 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_137 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX2" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_138 = NL + "\t";
  protected final String TEXT_139 = " get";
  protected final String TEXT_140 = "(int index);" + NL;
  protected final String TEXT_141 = NL + "\tpublic ";
  protected final String TEXT_142 = " get";
  protected final String TEXT_143 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_144 = "(";
  protected final String TEXT_145 = ")";
  protected final String TEXT_146 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_147 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX3" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_148 = NL + "\tint get";
  protected final String TEXT_149 = "Length();" + NL;
  protected final String TEXT_150 = NL + "\tpublic int get";
  protected final String TEXT_151 = "Length()" + NL + "\t{";
  protected final String TEXT_152 = NL + "\t\treturn ";
  protected final String TEXT_153 = "().size();";
  protected final String TEXT_154 = NL + "\t\treturn ";
  protected final String TEXT_155 = " == null ? 0 : ";
  protected final String TEXT_156 = ".size();";
  protected final String TEXT_157 = NL + "\t}" + NL;
  protected final String TEXT_158 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX4" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_159 = NL + "\tvoid set";
  protected final String TEXT_160 = "(";
  protected final String TEXT_161 = "[] new";
  protected final String TEXT_162 = ");" + NL;
  protected final String TEXT_163 = NL + "\tpublic void set";
  protected final String TEXT_164 = "(";
  protected final String TEXT_165 = "[] new";
  protected final String TEXT_166 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_167 = ")";
  protected final String TEXT_168 = "()).setData(new";
  protected final String TEXT_169 = ".length, new";
  protected final String TEXT_170 = ");" + NL + "\t}" + NL;
  protected final String TEXT_171 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX5" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_172 = NL + "\tvoid set";
  protected final String TEXT_173 = "(int index, ";
  protected final String TEXT_174 = " element);" + NL;
  protected final String TEXT_175 = NL + "\tpublic void set";
  protected final String TEXT_176 = "(int index, ";
  protected final String TEXT_177 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_178 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_179 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_180 = "</b></em>' ";
  protected final String TEXT_181 = ".";
  protected final String TEXT_182 = NL + "\t * The key is of type ";
  protected final String TEXT_183 = "list of {@link ";
  protected final String TEXT_184 = "}";
  protected final String TEXT_185 = "{@link ";
  protected final String TEXT_186 = "}";
  protected final String TEXT_187 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_188 = "list of {@link ";
  protected final String TEXT_189 = "}";
  protected final String TEXT_190 = "{@link ";
  protected final String TEXT_191 = "}";
  protected final String TEXT_192 = ",";
  protected final String TEXT_193 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_194 = "}";
  protected final String TEXT_195 = ".";
  protected final String TEXT_196 = NL + "\t * The default value is <code>";
  protected final String TEXT_197 = "</code>.";
  protected final String TEXT_198 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_199 = "}.";
  protected final String TEXT_200 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_201 = "#";
  protected final String TEXT_202 = " <em>";
  protected final String TEXT_203 = "</em>}'.";
  protected final String TEXT_204 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX6a";
  protected final String TEXT_205 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_206 = "</em>' ";
  protected final String TEXT_207 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_208 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_209 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t *XX6b" + NL + "\t * ";
  protected final String TEXT_210 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_211 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_212 = "</em>' ";
  protected final String TEXT_213 = ".";
  protected final String TEXT_214 = NL + "\t * @see ";
  protected final String TEXT_215 = NL + "\t * @see #isSet";
  protected final String TEXT_216 = "()";
  protected final String TEXT_217 = NL + "\t * @see #unset";
  protected final String TEXT_218 = "()";
  protected final String TEXT_219 = NL + "\t * @see #set";
  protected final String TEXT_220 = "(";
  protected final String TEXT_221 = ")";
  protected final String TEXT_222 = NL + "\t * @see ";
  protected final String TEXT_223 = "#get";
  protected final String TEXT_224 = "()";
  protected final String TEXT_225 = NL + "\t * @see ";
  protected final String TEXT_226 = "#";
  protected final String TEXT_227 = NL + "\t * @model ";
  protected final String TEXT_228 = NL + "\t *        ";
  protected final String TEXT_229 = NL + "\t * @model";
  protected final String TEXT_230 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_231 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX7" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_232 = NL + "\t";
  protected final String TEXT_233 = " ";
  protected final String TEXT_234 = "();";
  protected final String TEXT_235 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_236 = NL + "\tpublic ";
  protected final String TEXT_237 = " ";
  protected final String TEXT_238 = "_";
  protected final String TEXT_239 = "()" + NL + "\t{" + NL + "\t\ttry {" + NL + "\t\tloadingOnDemand = true;";
  protected final String TEXT_240 = NL + "\t\treturn ";
  protected final String TEXT_241 = "(";
  protected final String TEXT_242 = "(";
  protected final String TEXT_243 = ")eDynamicGet(";
  protected final String TEXT_244 = ", ";
  protected final String TEXT_245 = ", true, ";
  protected final String TEXT_246 = ")";
  protected final String TEXT_247 = ").";
  protected final String TEXT_248 = "()";
  protected final String TEXT_249 = ";";
  protected final String TEXT_250 = NL + "\t\treturn ";
  protected final String TEXT_251 = "(";
  protected final String TEXT_252 = "(";
  protected final String TEXT_253 = ")eGet(";
  protected final String TEXT_254 = ", true)";
  protected final String TEXT_255 = ").";
  protected final String TEXT_256 = "()";
  protected final String TEXT_257 = ";";
  protected final String TEXT_258 = NL + "\t\treturn ";
  protected final String TEXT_259 = "(";
  protected final String TEXT_260 = "(";
  protected final String TEXT_261 = ")";
  protected final String TEXT_262 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_263 = ").";
  protected final String TEXT_264 = "()";
  protected final String TEXT_265 = ";";
  protected final String TEXT_266 = "      " + NL + "\t\t";
  protected final String TEXT_267 = " ";
  protected final String TEXT_268 = " = (";
  protected final String TEXT_269 = ")eVirtualGet(";
  protected final String TEXT_270 = ");" + NL + "\t  ";
  protected final String TEXT_271 = "\t" + NL + "\t  ";
  protected final String TEXT_272 = " " + NL + "\t\t";
  protected final String TEXT_273 = NL + "\t\tif (";
  protected final String TEXT_274 = " == null){" + NL + "\t\t";
  protected final String TEXT_275 = NL + "\t\tif (";
  protected final String TEXT_276 = " == null){";
  protected final String TEXT_277 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_278 = ", ";
  protected final String TEXT_279 = " = new ";
  protected final String TEXT_280 = ");";
  protected final String TEXT_281 = " = new ";
  protected final String TEXT_282 = ";" + NL + "\t\t\tif (isLoaded()) " + NL + "\t\t\t((";
  protected final String TEXT_283 = ") this.eResource()).getOnDemand(this, ";
  protected final String TEXT_284 = ");\t\t\t";
  protected final String TEXT_285 = "}" + NL + "\t\treturn ";
  protected final String TEXT_286 = ";";
  protected final String TEXT_287 = NL + "\t\tif (isLoaded() && eContainer() == null) {" + NL + "\t\t\t";
  protected final String TEXT_288 = " ";
  protected final String TEXT_289 = " = (";
  protected final String TEXT_290 = ") ((";
  protected final String TEXT_291 = ") this.eResource()).getContainerOnDemand(this, ";
  protected final String TEXT_292 = ");" + NL + "\t\t\tbasicSet";
  protected final String TEXT_293 = "(";
  protected final String TEXT_294 = ",null);}" + NL + "\t\treturn (";
  protected final String TEXT_295 = ")eContainer();";
  protected final String TEXT_296 = NL + "\t\t";
  protected final String TEXT_297 = " ";
  protected final String TEXT_298 = " = (";
  protected final String TEXT_299 = ")eVirtualGet(";
  protected final String TEXT_300 = ", Data";
  protected final String TEXT_301 = ".";
  protected final String TEXT_302 = ");";
  protected final String TEXT_303 = NL + "\t\tif (";
  protected final String TEXT_304 = " == null && isLoaded())" + NL + "\t\t{" + NL + "\t\t\t((";
  protected final String TEXT_305 = ") this.eResource()).getOnDemand(this, ";
  protected final String TEXT_306 = ");" + NL + "\t\t}";
  protected final String TEXT_307 = "\t\t";
  protected final String TEXT_308 = NL + "\t\treturn (";
  protected final String TEXT_309 = ")eVirtualGet(";
  protected final String TEXT_310 = ", Data";
  protected final String TEXT_311 = ".";
  protected final String TEXT_312 = ");";
  protected final String TEXT_313 = NL + "\t\treturn (";
  protected final String TEXT_314 = " & Data";
  protected final String TEXT_315 = ".";
  protected final String TEXT_316 = "_EFLAG) != 0;";
  protected final String TEXT_317 = NL + "\t\treturn Data";
  protected final String TEXT_318 = ".";
  protected final String TEXT_319 = "_EFLAG_VALUES[(";
  protected final String TEXT_320 = " & Data";
  protected final String TEXT_321 = ".";
  protected final String TEXT_322 = "_EFLAG) >>> Data";
  protected final String TEXT_323 = ".";
  protected final String TEXT_324 = "_EFLAG_OFFSET];";
  protected final String TEXT_325 = NL + "\t\tif (isLoaded()) {" + NL + "\t\t\t((";
  protected final String TEXT_326 = ") this.eResource()).getOnDemand(this, ";
  protected final String TEXT_327 = ");" + NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_328 = ";";
  protected final String TEXT_329 = NL + "\t\treturn ";
  protected final String TEXT_330 = ";" + NL + "\t\t";
  protected final String TEXT_331 = NL + "\t\t";
  protected final String TEXT_332 = " ";
  protected final String TEXT_333 = " = basicGet";
  protected final String TEXT_334 = "();" + NL + "\t\treturn ";
  protected final String TEXT_335 = " != null && ";
  protected final String TEXT_336 = ".eIsProxy() ? ";
  protected final String TEXT_337 = "eResolveProxy((";
  protected final String TEXT_338 = ")";
  protected final String TEXT_339 = ") : ";
  protected final String TEXT_340 = ";";
  protected final String TEXT_341 = NL + "\t\treturn new ";
  protected final String TEXT_342 = "((";
  protected final String TEXT_343 = ".Internal)((";
  protected final String TEXT_344 = ".Internal.Wrapper)get";
  protected final String TEXT_345 = "()).featureMap().";
  protected final String TEXT_346 = "list(";
  protected final String TEXT_347 = "));";
  protected final String TEXT_348 = NL + "\t\treturn (";
  protected final String TEXT_349 = ")get";
  protected final String TEXT_350 = "().";
  protected final String TEXT_351 = "list(";
  protected final String TEXT_352 = ");";
  protected final String TEXT_353 = NL + "\t\treturn ((";
  protected final String TEXT_354 = ".Internal.Wrapper)get";
  protected final String TEXT_355 = "()).featureMap().list(";
  protected final String TEXT_356 = ");";
  protected final String TEXT_357 = NL + "\t\treturn get";
  protected final String TEXT_358 = "().list(";
  protected final String TEXT_359 = ");";
  protected final String TEXT_360 = NL + "\t\treturn ";
  protected final String TEXT_361 = "(";
  protected final String TEXT_362 = "(";
  protected final String TEXT_363 = ")";
  protected final String TEXT_364 = "((";
  protected final String TEXT_365 = ".Internal.Wrapper)get";
  protected final String TEXT_366 = "()).featureMap().get(";
  protected final String TEXT_367 = ", true)";
  protected final String TEXT_368 = ").";
  protected final String TEXT_369 = "()";
  protected final String TEXT_370 = ";";
  protected final String TEXT_371 = NL + "\t\treturn ";
  protected final String TEXT_372 = "(";
  protected final String TEXT_373 = "(";
  protected final String TEXT_374 = ")";
  protected final String TEXT_375 = "get";
  protected final String TEXT_376 = "().get(";
  protected final String TEXT_377 = ", true)";
  protected final String TEXT_378 = ").";
  protected final String TEXT_379 = "()";
  protected final String TEXT_380 = ";";
  protected final String TEXT_381 = NL + "\t\t";
  protected final String TEXT_382 = NL + "\t\t";
  protected final String TEXT_383 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_384 = "' ";
  protected final String TEXT_385 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_386 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_387 = "EcoreEMap";
  protected final String TEXT_388 = "BasicFeatureMap";
  protected final String TEXT_389 = "EcoreEList";
  protected final String TEXT_390 = " should be used.";
  protected final String TEXT_391 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_392 = NL + " \t  \t} finally {" + NL + "\t\t\tloadingOnDemand = false;" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_393 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_394 = NL + "\tpublic ";
  protected final String TEXT_395 = " basicGet";
  protected final String TEXT_396 = "()" + NL + "\t{";
  protected final String TEXT_397 = NL + "\t\treturn (";
  protected final String TEXT_398 = ")eDynamicGet(";
  protected final String TEXT_399 = ", ";
  protected final String TEXT_400 = ", false, ";
  protected final String TEXT_401 = ");";
  protected final String TEXT_402 = NL + "\t\treturn ";
  protected final String TEXT_403 = "(";
  protected final String TEXT_404 = "(";
  protected final String TEXT_405 = ")";
  protected final String TEXT_406 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_407 = ").";
  protected final String TEXT_408 = "()";
  protected final String TEXT_409 = ";";
  protected final String TEXT_410 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_411 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_412 = ")eInternalContainer();";
  protected final String TEXT_413 = NL + "\t\treturn (";
  protected final String TEXT_414 = ")eVirtualGet(";
  protected final String TEXT_415 = ");";
  protected final String TEXT_416 = NL + "\t\treturn data != null ? ";
  protected final String TEXT_417 = " : null;";
  protected final String TEXT_418 = NL + "\t\treturn (";
  protected final String TEXT_419 = ")((";
  protected final String TEXT_420 = ".Internal.Wrapper)get";
  protected final String TEXT_421 = "()).featureMap().get(";
  protected final String TEXT_422 = ", false);";
  protected final String TEXT_423 = NL + "\t\treturn (";
  protected final String TEXT_424 = ")get";
  protected final String TEXT_425 = "().get(";
  protected final String TEXT_426 = ", false);";
  protected final String TEXT_427 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_428 = "' ";
  protected final String TEXT_429 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_430 = NL + "\t}" + NL;
  protected final String TEXT_431 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_432 = NL + "\tpublic ";
  protected final String TEXT_433 = " basicSet";
  protected final String TEXT_434 = "(";
  protected final String TEXT_435 = " new";
  protected final String TEXT_436 = ", ";
  protected final String TEXT_437 = " msgs)" + NL + "\t{" + NL + "\t";
  protected final String TEXT_438 = NL + "\t\t" + NL + "\t";
  protected final String TEXT_439 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_440 = ")new";
  protected final String TEXT_441 = ", ";
  protected final String TEXT_442 = ", msgs);";
  protected final String TEXT_443 = NL + "\t\treturn msgs;";
  protected final String TEXT_444 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_445 = ")new";
  protected final String TEXT_446 = ", ";
  protected final String TEXT_447 = ", msgs);";
  protected final String TEXT_448 = NL + "\t\treturn msgs;";
  protected final String TEXT_449 = NL + "\t\tObject old";
  protected final String TEXT_450 = " = eVirtualSet(";
  protected final String TEXT_451 = ", new";
  protected final String TEXT_452 = ");";
  protected final String TEXT_453 = NL + "\t\t";
  protected final String TEXT_454 = " old";
  protected final String TEXT_455 = " = ";
  protected final String TEXT_456 = ";" + NL + "\t\t";
  protected final String TEXT_457 = " = new";
  protected final String TEXT_458 = ";";
  protected final String TEXT_459 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_460 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_461 = NL + "\t\tboolean old";
  protected final String TEXT_462 = "ESet = (";
  protected final String TEXT_463 = " & ";
  protected final String TEXT_464 = "_ESETFLAG) != 0;";
  protected final String TEXT_465 = NL + "\t\t";
  protected final String TEXT_466 = " |= ";
  protected final String TEXT_467 = "_ESETFLAG;";
  protected final String TEXT_468 = NL + "\t\tboolean old";
  protected final String TEXT_469 = "ESet = ";
  protected final String TEXT_470 = "ESet;";
  protected final String TEXT_471 = NL + "\t\t";
  protected final String TEXT_472 = "ESet = true;";
  protected final String TEXT_473 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_474 = NL + "\t\t\t";
  protected final String TEXT_475 = " notification = new ";
  protected final String TEXT_476 = "(this, ";
  protected final String TEXT_477 = ".SET, ";
  protected final String TEXT_478 = ", ";
  protected final String TEXT_479 = "isSetChange ? null : old";
  protected final String TEXT_480 = "old";
  protected final String TEXT_481 = ", new";
  protected final String TEXT_482 = ", ";
  protected final String TEXT_483 = "isSetChange";
  protected final String TEXT_484 = "!old";
  protected final String TEXT_485 = "ESet";
  protected final String TEXT_486 = ");";
  protected final String TEXT_487 = NL + "\t\t\t";
  protected final String TEXT_488 = " notification = new ";
  protected final String TEXT_489 = "(this, ";
  protected final String TEXT_490 = ".SET, ";
  protected final String TEXT_491 = ", ";
  protected final String TEXT_492 = "old";
  protected final String TEXT_493 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_494 = "old";
  protected final String TEXT_495 = ", new";
  protected final String TEXT_496 = ");";
  protected final String TEXT_497 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_498 = NL + "\t\treturn msgs;";
  protected final String TEXT_499 = NL + "\t\treturn ((";
  protected final String TEXT_500 = ".Internal)((";
  protected final String TEXT_501 = ".Internal.Wrapper)get";
  protected final String TEXT_502 = "()).featureMap()).basicAdd(";
  protected final String TEXT_503 = ", new";
  protected final String TEXT_504 = ", msgs);";
  protected final String TEXT_505 = NL + "\t\treturn ((";
  protected final String TEXT_506 = ".Internal)get";
  protected final String TEXT_507 = "()).basicAdd(";
  protected final String TEXT_508 = ", new";
  protected final String TEXT_509 = ", msgs);";
  protected final String TEXT_510 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_511 = "' ";
  protected final String TEXT_512 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_513 = NL + "\t}" + NL;
  protected final String TEXT_514 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_515 = "#";
  protected final String TEXT_516 = " <em>";
  protected final String TEXT_517 = "</em>}' ";
  protected final String TEXT_518 = ".";
  protected final String TEXT_519 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY1" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_520 = "</em>' ";
  protected final String TEXT_521 = ".";
  protected final String TEXT_522 = NL + "\t * @see ";
  protected final String TEXT_523 = NL + "\t * @see #isSet";
  protected final String TEXT_524 = "()";
  protected final String TEXT_525 = NL + "\t * @see #unset";
  protected final String TEXT_526 = "()";
  protected final String TEXT_527 = NL + "\t * @see #";
  protected final String TEXT_528 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_529 = NL + " /**" + NL + " * <!-- begin-user-doc -->" + NL + " *YY2" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */";
  protected final String TEXT_530 = NL + "\tvoid set";
  protected final String TEXT_531 = "(";
  protected final String TEXT_532 = " value);" + NL;
  protected final String TEXT_533 = NL + "\tpublic void set";
  protected final String TEXT_534 = "_";
  protected final String TEXT_535 = "(";
  protected final String TEXT_536 = " ";
  protected final String TEXT_537 = ")" + NL + "\t{";
  protected final String TEXT_538 = NL + "\t";
  protected final String TEXT_539 = NL + "\t\t";
  protected final String TEXT_540 = NL + "\t";
  protected final String TEXT_541 = NL + "\t\teDynamicSet(";
  protected final String TEXT_542 = ", ";
  protected final String TEXT_543 = ", ";
  protected final String TEXT_544 = "new ";
  protected final String TEXT_545 = "(";
  protected final String TEXT_546 = "new";
  protected final String TEXT_547 = ")";
  protected final String TEXT_548 = ");";
  protected final String TEXT_549 = NL + "\t\teSet(";
  protected final String TEXT_550 = ", ";
  protected final String TEXT_551 = "new ";
  protected final String TEXT_552 = "(";
  protected final String TEXT_553 = "new";
  protected final String TEXT_554 = ")";
  protected final String TEXT_555 = ");";
  protected final String TEXT_556 = NL + "\t\t";
  protected final String TEXT_557 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_558 = "new ";
  protected final String TEXT_559 = "(";
  protected final String TEXT_560 = "new";
  protected final String TEXT_561 = ")";
  protected final String TEXT_562 = ");";
  protected final String TEXT_563 = NL + "\t\tif (new";
  protected final String TEXT_564 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_565 = " && new";
  protected final String TEXT_566 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_567 = ".isAncestor(this, ";
  protected final String TEXT_568 = "new";
  protected final String TEXT_569 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_570 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_571 = NL + "\t\t\t";
  protected final String TEXT_572 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_573 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_574 = ")new";
  protected final String TEXT_575 = ").eInverseAdd(this, ";
  protected final String TEXT_576 = ", ";
  protected final String TEXT_577 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_578 = "(";
  protected final String TEXT_579 = "new";
  protected final String TEXT_580 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_581 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_582 = "(this, ";
  protected final String TEXT_583 = ".SET, ";
  protected final String TEXT_584 = ", new";
  protected final String TEXT_585 = ", new";
  protected final String TEXT_586 = "));";
  protected final String TEXT_587 = NL + "\t\t";
  protected final String TEXT_588 = " ";
  protected final String TEXT_589 = " = (";
  protected final String TEXT_590 = ")eVirtualGet(";
  protected final String TEXT_591 = ");";
  protected final String TEXT_592 = NL + "\t\tif (new";
  protected final String TEXT_593 = " != ";
  protected final String TEXT_594 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_595 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_596 = " != null)";
  protected final String TEXT_597 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_598 = ") ";
  protected final String TEXT_599 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_600 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_601 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_602 = ")new";
  protected final String TEXT_603 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_604 = ", null, msgs);";
  protected final String TEXT_605 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_606 = ") ";
  protected final String TEXT_607 = ").eInverseRemove(this, ";
  protected final String TEXT_608 = ", ";
  protected final String TEXT_609 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_610 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_611 = ")new";
  protected final String TEXT_612 = ").eInverseAdd(this, ";
  protected final String TEXT_613 = ", ";
  protected final String TEXT_614 = ".class, msgs);";
  protected final String TEXT_615 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_616 = "(";
  protected final String TEXT_617 = "new";
  protected final String TEXT_618 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_619 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_620 = NL + "\t\t\tboolean old";
  protected final String TEXT_621 = "ESet = eVirtualIsSet(";
  protected final String TEXT_622 = ");";
  protected final String TEXT_623 = NL + "\t\t\tboolean old";
  protected final String TEXT_624 = "ESet = (";
  protected final String TEXT_625 = " & ";
  protected final String TEXT_626 = "_ESETFLAG) != 0;";
  protected final String TEXT_627 = NL + "\t\t\t";
  protected final String TEXT_628 = " |= ";
  protected final String TEXT_629 = "_ESETFLAG;";
  protected final String TEXT_630 = NL + "\t\t\tboolean old";
  protected final String TEXT_631 = "ESet = ";
  protected final String TEXT_632 = "ESet;";
  protected final String TEXT_633 = NL + "\t\t\t";
  protected final String TEXT_634 = "ESet = true;";
  protected final String TEXT_635 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_636 = "(this, ";
  protected final String TEXT_637 = ".SET, ";
  protected final String TEXT_638 = ", new";
  protected final String TEXT_639 = ", new";
  protected final String TEXT_640 = ", !old";
  protected final String TEXT_641 = "ESet));";
  protected final String TEXT_642 = NL + "\t\t}";
  protected final String TEXT_643 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_644 = "(this, ";
  protected final String TEXT_645 = ".SET, ";
  protected final String TEXT_646 = ", new";
  protected final String TEXT_647 = ", new";
  protected final String TEXT_648 = "));";
  protected final String TEXT_649 = NL + "\t\t";
  protected final String TEXT_650 = " old";
  protected final String TEXT_651 = " = (";
  protected final String TEXT_652 = " & Data";
  protected final String TEXT_653 = ".";
  protected final String TEXT_654 = "_EFLAG) != 0;";
  protected final String TEXT_655 = NL + "\t\t";
  protected final String TEXT_656 = " old";
  protected final String TEXT_657 = " = ";
  protected final String TEXT_658 = "_EFLAG_VALUES[(";
  protected final String TEXT_659 = " & Data";
  protected final String TEXT_660 = ".";
  protected final String TEXT_661 = "_EFLAG) >>> ";
  protected final String TEXT_662 = "_EFLAG_OFFSET];";
  protected final String TEXT_663 = NL + "\t\tif (new";
  protected final String TEXT_664 = ") ";
  protected final String TEXT_665 = " |= Data";
  protected final String TEXT_666 = ".";
  protected final String TEXT_667 = "_EFLAG; else ";
  protected final String TEXT_668 = " &= Data";
  protected final String TEXT_669 = ".";
  protected final String TEXT_670 = "_EFLAG;";
  protected final String TEXT_671 = NL + "\t\tif (new";
  protected final String TEXT_672 = " == null) new";
  protected final String TEXT_673 = " = ";
  protected final String TEXT_674 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_675 = " = ";
  protected final String TEXT_676 = " & Data";
  protected final String TEXT_677 = ".";
  protected final String TEXT_678 = "_EFLAG | ";
  protected final String TEXT_679 = "new";
  protected final String TEXT_680 = ".ordinal()";
  protected final String TEXT_681 = ".VALUES.indexOf(new";
  protected final String TEXT_682 = ")";
  protected final String TEXT_683 = " << ";
  protected final String TEXT_684 = "_EFLAG_OFFSET;";
  protected final String TEXT_685 = NL + "\t\t";
  protected final String TEXT_686 = " old";
  protected final String TEXT_687 = " = ";
  protected final String TEXT_688 = ";";
  protected final String TEXT_689 = NL + "\t\t";
  protected final String TEXT_690 = " ";
  protected final String TEXT_691 = " = new";
  protected final String TEXT_692 = " == null ? Data";
  protected final String TEXT_693 = ".";
  protected final String TEXT_694 = " : new";
  protected final String TEXT_695 = ";";
  protected final String TEXT_696 = NL + "\t\t";
  protected final String TEXT_697 = " = new";
  protected final String TEXT_698 = " == null ? ";
  protected final String TEXT_699 = " : new";
  protected final String TEXT_700 = ";";
  protected final String TEXT_701 = NL + "\t\t";
  protected final String TEXT_702 = " ";
  protected final String TEXT_703 = " = ";
  protected final String TEXT_704 = "new";
  protected final String TEXT_705 = ";";
  protected final String TEXT_706 = NL + "\t\t";
  protected final String TEXT_707 = " = ";
  protected final String TEXT_708 = "new";
  protected final String TEXT_709 = ";";
  protected final String TEXT_710 = NL + "\t\tObject old";
  protected final String TEXT_711 = " = eVirtualSet(";
  protected final String TEXT_712 = ", ";
  protected final String TEXT_713 = ");";
  protected final String TEXT_714 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_715 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_716 = NL + "\t\tboolean old";
  protected final String TEXT_717 = "ESet = (";
  protected final String TEXT_718 = " & ";
  protected final String TEXT_719 = "_ESETFLAG) != 0;";
  protected final String TEXT_720 = NL + "\t\t";
  protected final String TEXT_721 = " |= ";
  protected final String TEXT_722 = "_ESETFLAG;";
  protected final String TEXT_723 = NL + "\t\tboolean old";
  protected final String TEXT_724 = "ESet = ";
  protected final String TEXT_725 = "ESet;";
  protected final String TEXT_726 = NL + "\t\t";
  protected final String TEXT_727 = "ESet = true;";
  protected final String TEXT_728 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_729 = NL + "\t\t\t(this, ";
  protected final String TEXT_730 = ".SET," + NL + "\t\t\t";
  protected final String TEXT_731 = ", " + NL + "\t\t\t";
  protected final String TEXT_732 = "isSetChange ? Data";
  protected final String TEXT_733 = ".";
  protected final String TEXT_734 = " : old";
  protected final String TEXT_735 = "old";
  protected final String TEXT_736 = ", ";
  protected final String TEXT_737 = "new";
  protected final String TEXT_738 = ", ";
  protected final String TEXT_739 = "isSetChange";
  protected final String TEXT_740 = "!old";
  protected final String TEXT_741 = "ESet";
  protected final String TEXT_742 = "));";
  protected final String TEXT_743 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_744 = "(" + NL + "\t\t\tthis, ";
  protected final String TEXT_745 = ".SET," + NL + "\t\t\t";
  protected final String TEXT_746 = "," + NL + "\t\t\t";
  protected final String TEXT_747 = NL + "\t\t\t\told";
  protected final String TEXT_748 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_749 = " : old";
  protected final String TEXT_750 = "old";
  protected final String TEXT_751 = ", ";
  protected final String TEXT_752 = "new";
  protected final String TEXT_753 = "));";
  protected final String TEXT_754 = NL + "\t\t((";
  protected final String TEXT_755 = ".Internal)((";
  protected final String TEXT_756 = ".Internal.Wrapper)get";
  protected final String TEXT_757 = "()).featureMap()).set(";
  protected final String TEXT_758 = ", ";
  protected final String TEXT_759 = "new ";
  protected final String TEXT_760 = "(";
  protected final String TEXT_761 = "new";
  protected final String TEXT_762 = ")";
  protected final String TEXT_763 = ");";
  protected final String TEXT_764 = NL + "\t\t((";
  protected final String TEXT_765 = ".Internal)get";
  protected final String TEXT_766 = "()).set(";
  protected final String TEXT_767 = ", ";
  protected final String TEXT_768 = "new ";
  protected final String TEXT_769 = "(";
  protected final String TEXT_770 = "new";
  protected final String TEXT_771 = ")";
  protected final String TEXT_772 = ");";
  protected final String TEXT_773 = NL + "\t\t";
  protected final String TEXT_774 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_775 = "' ";
  protected final String TEXT_776 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_777 = NL + "    addChangelogEntry(new";
  protected final String TEXT_778 = ", ";
  protected final String TEXT_779 = ");" + NL + "\t}";
  protected final String TEXT_780 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY3" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_781 = NL + "\tpublic ";
  protected final String TEXT_782 = " basicUnset";
  protected final String TEXT_783 = "(";
  protected final String TEXT_784 = " msgs)" + NL + "\t{";
  protected final String TEXT_785 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_786 = ")";
  protected final String TEXT_787 = "basicGet";
  protected final String TEXT_788 = "(), ";
  protected final String TEXT_789 = ", msgs);";
  protected final String TEXT_790 = "Object old";
  protected final String TEXT_791 = " = ";
  protected final String TEXT_792 = "eVirtualUnset(";
  protected final String TEXT_793 = ");";
  protected final String TEXT_794 = NL + "\t\t";
  protected final String TEXT_795 = " old";
  protected final String TEXT_796 = " = ";
  protected final String TEXT_797 = ";";
  protected final String TEXT_798 = NL + "\t\t";
  protected final String TEXT_799 = " = null;";
  protected final String TEXT_800 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_801 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_802 = NL + "\t\tboolean old";
  protected final String TEXT_803 = "ESet = (";
  protected final String TEXT_804 = " & ";
  protected final String TEXT_805 = "_ESETFLAG) != 0;";
  protected final String TEXT_806 = NL + "\t\t";
  protected final String TEXT_807 = " &= ~";
  protected final String TEXT_808 = "_ESETFLAG;";
  protected final String TEXT_809 = NL + "\t\tboolean old";
  protected final String TEXT_810 = "ESet = ";
  protected final String TEXT_811 = "ESet;";
  protected final String TEXT_812 = NL + "\t\t";
  protected final String TEXT_813 = "ESet = false;";
  protected final String TEXT_814 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_815 = " notification = new ";
  protected final String TEXT_816 = "(this, ";
  protected final String TEXT_817 = ".UNSET, ";
  protected final String TEXT_818 = ", ";
  protected final String TEXT_819 = "isSetChange ? old";
  protected final String TEXT_820 = " : null";
  protected final String TEXT_821 = "old";
  protected final String TEXT_822 = ", null, ";
  protected final String TEXT_823 = "isSetChange";
  protected final String TEXT_824 = "old";
  protected final String TEXT_825 = "ESet";
  protected final String TEXT_826 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_827 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_828 = "' ";
  protected final String TEXT_829 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_830 = NL + "\t}" + NL;
  protected final String TEXT_831 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_832 = "#";
  protected final String TEXT_833 = " <em>";
  protected final String TEXT_834 = "</em>}' ";
  protected final String TEXT_835 = ".";
  protected final String TEXT_836 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY4" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_837 = NL + "\t * @see #isSet";
  protected final String TEXT_838 = "()";
  protected final String TEXT_839 = NL + "\t * @see #";
  protected final String TEXT_840 = "()";
  protected final String TEXT_841 = NL + "\t * @see #set";
  protected final String TEXT_842 = "(";
  protected final String TEXT_843 = ")";
  protected final String TEXT_844 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_845 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY5" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_846 = NL + "\tvoid unset";
  protected final String TEXT_847 = "();" + NL;
  protected final String TEXT_848 = NL + "\tpublic void unset";
  protected final String TEXT_849 = "_";
  protected final String TEXT_850 = "()" + NL + "\t{";
  protected final String TEXT_851 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_852 = ", ";
  protected final String TEXT_853 = ");";
  protected final String TEXT_854 = NL + "\t\teUnset(";
  protected final String TEXT_855 = ");";
  protected final String TEXT_856 = NL + "\t\t";
  protected final String TEXT_857 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_858 = NL + "\t\t";
  protected final String TEXT_859 = " ";
  protected final String TEXT_860 = " = (";
  protected final String TEXT_861 = ")eVirtualGet(";
  protected final String TEXT_862 = ");";
  protected final String TEXT_863 = NL + "\t\tif (";
  protected final String TEXT_864 = " != null) ((";
  protected final String TEXT_865 = ".Unsettable";
  protected final String TEXT_866 = ")";
  protected final String TEXT_867 = ").unset();";
  protected final String TEXT_868 = NL + "\t\t";
  protected final String TEXT_869 = " ";
  protected final String TEXT_870 = " = (";
  protected final String TEXT_871 = ")eVirtualGet(";
  protected final String TEXT_872 = ");";
  protected final String TEXT_873 = NL + "\t\tif (";
  protected final String TEXT_874 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_875 = " msgs = null;";
  protected final String TEXT_876 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_877 = ")";
  protected final String TEXT_878 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_879 = ", null, msgs);";
  protected final String TEXT_880 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_881 = ")";
  protected final String TEXT_882 = ").eInverseRemove(this, ";
  protected final String TEXT_883 = ", ";
  protected final String TEXT_884 = ".class, msgs);";
  protected final String TEXT_885 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_886 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_887 = NL + "\t\t\tboolean old";
  protected final String TEXT_888 = "ESet = eVirtualIsSet(";
  protected final String TEXT_889 = ");";
  protected final String TEXT_890 = NL + "\t\t\tboolean old";
  protected final String TEXT_891 = "ESet = (";
  protected final String TEXT_892 = " & ";
  protected final String TEXT_893 = "_ESETFLAG) != 0;";
  protected final String TEXT_894 = NL + "\t\t\t";
  protected final String TEXT_895 = " &= ~";
  protected final String TEXT_896 = "_ESETFLAG;";
  protected final String TEXT_897 = NL + "\t\t\tboolean old";
  protected final String TEXT_898 = "ESet = ";
  protected final String TEXT_899 = "ESet;";
  protected final String TEXT_900 = NL + "\t\t\t";
  protected final String TEXT_901 = "ESet = false;";
  protected final String TEXT_902 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_903 = "(this, ";
  protected final String TEXT_904 = ".UNSET, ";
  protected final String TEXT_905 = ", null, null, old";
  protected final String TEXT_906 = "ESet));";
  protected final String TEXT_907 = NL + "\t\t}";
  protected final String TEXT_908 = NL + "\t\t";
  protected final String TEXT_909 = " old";
  protected final String TEXT_910 = " = (";
  protected final String TEXT_911 = " & ";
  protected final String TEXT_912 = "_EFLAG) != 0;";
  protected final String TEXT_913 = NL + "\t\t";
  protected final String TEXT_914 = " old";
  protected final String TEXT_915 = " = ";
  protected final String TEXT_916 = "_EFLAG_VALUES[(";
  protected final String TEXT_917 = " & ";
  protected final String TEXT_918 = "_EFLAG) >>> ";
  protected final String TEXT_919 = "_EFLAG_OFFSET];";
  protected final String TEXT_920 = NL + "\t\tObject old";
  protected final String TEXT_921 = " = eVirtualUnset(";
  protected final String TEXT_922 = ");";
  protected final String TEXT_923 = NL + "\t\t";
  protected final String TEXT_924 = " old";
  protected final String TEXT_925 = " = ";
  protected final String TEXT_926 = ";";
  protected final String TEXT_927 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_928 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_929 = NL + "\t\tboolean old";
  protected final String TEXT_930 = "ESet = (";
  protected final String TEXT_931 = " & ";
  protected final String TEXT_932 = "_ESETFLAG) != 0;";
  protected final String TEXT_933 = NL + "\t\tboolean old";
  protected final String TEXT_934 = "ESet = ";
  protected final String TEXT_935 = "ESet;";
  protected final String TEXT_936 = NL + "\t\t";
  protected final String TEXT_937 = " = null;";
  protected final String TEXT_938 = NL + "\t\t";
  protected final String TEXT_939 = " &= ~";
  protected final String TEXT_940 = "_ESETFLAG;";
  protected final String TEXT_941 = NL + "\t\t";
  protected final String TEXT_942 = "ESet = false;";
  protected final String TEXT_943 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_944 = "(this, ";
  protected final String TEXT_945 = ".UNSET, ";
  protected final String TEXT_946 = ", ";
  protected final String TEXT_947 = "isSetChange ? old";
  protected final String TEXT_948 = " : null";
  protected final String TEXT_949 = "old";
  protected final String TEXT_950 = ", null, ";
  protected final String TEXT_951 = "isSetChange";
  protected final String TEXT_952 = "old";
  protected final String TEXT_953 = "ESet";
  protected final String TEXT_954 = "));";
  protected final String TEXT_955 = NL + "\t\tif (";
  protected final String TEXT_956 = ") ";
  protected final String TEXT_957 = " |= ";
  protected final String TEXT_958 = "_EFLAG; else ";
  protected final String TEXT_959 = " &= ~";
  protected final String TEXT_960 = "_EFLAG;";
  protected final String TEXT_961 = NL + "\t\t";
  protected final String TEXT_962 = " = ";
  protected final String TEXT_963 = " & ~";
  protected final String TEXT_964 = "_EFLAG | ";
  protected final String TEXT_965 = "_EFLAG_DEFAULT;";
  protected final String TEXT_966 = NL + "\t\t";
  protected final String TEXT_967 = " = ";
  protected final String TEXT_968 = ";";
  protected final String TEXT_969 = NL + "\t\t";
  protected final String TEXT_970 = " &= ~";
  protected final String TEXT_971 = "_ESETFLAG;";
  protected final String TEXT_972 = NL + "\t\t";
  protected final String TEXT_973 = "ESet = false;";
  protected final String TEXT_974 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_975 = "(this, ";
  protected final String TEXT_976 = ".UNSET, ";
  protected final String TEXT_977 = ", ";
  protected final String TEXT_978 = "isSetChange ? old";
  protected final String TEXT_979 = " : Data";
  protected final String TEXT_980 = ".";
  protected final String TEXT_981 = "old";
  protected final String TEXT_982 = ", Data";
  protected final String TEXT_983 = ".";
  protected final String TEXT_984 = ", ";
  protected final String TEXT_985 = "isSetChange";
  protected final String TEXT_986 = "old";
  protected final String TEXT_987 = "ESet";
  protected final String TEXT_988 = "));";
  protected final String TEXT_989 = NL + "\t\t((";
  protected final String TEXT_990 = ".Internal)((";
  protected final String TEXT_991 = ".Internal.Wrapper)get";
  protected final String TEXT_992 = "()).featureMap()).clear(";
  protected final String TEXT_993 = ");";
  protected final String TEXT_994 = NL + "\t\t((";
  protected final String TEXT_995 = ".Internal)get";
  protected final String TEXT_996 = "()).clear(";
  protected final String TEXT_997 = ");";
  protected final String TEXT_998 = NL + "\t\t";
  protected final String TEXT_999 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1000 = "' ";
  protected final String TEXT_1001 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1002 = NL + "\t}" + NL;
  protected final String TEXT_1003 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1004 = "#";
  protected final String TEXT_1005 = " <em>";
  protected final String TEXT_1006 = "</em>}' ";
  protected final String TEXT_1007 = " is set.";
  protected final String TEXT_1008 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY6" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1009 = "</em>' ";
  protected final String TEXT_1010 = " is set.";
  protected final String TEXT_1011 = NL + "\t * @see #unset";
  protected final String TEXT_1012 = "()";
  protected final String TEXT_1013 = NL + "\t * @see #";
  protected final String TEXT_1014 = "()";
  protected final String TEXT_1015 = NL + "\t * @see #set";
  protected final String TEXT_1016 = "(";
  protected final String TEXT_1017 = ")";
  protected final String TEXT_1018 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1019 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY7" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1020 = NL + "\tboolean isSet";
  protected final String TEXT_1021 = "();" + NL;
  protected final String TEXT_1022 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1023 = "_";
  protected final String TEXT_1024 = "()" + NL + "\t{";
  protected final String TEXT_1025 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1026 = ", ";
  protected final String TEXT_1027 = ");";
  protected final String TEXT_1028 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1029 = ");";
  protected final String TEXT_1030 = NL + "\t\treturn ";
  protected final String TEXT_1031 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1032 = NL + "\t\t";
  protected final String TEXT_1033 = " ";
  protected final String TEXT_1034 = " = (";
  protected final String TEXT_1035 = ")eVirtualGet(";
  protected final String TEXT_1036 = ");";
  protected final String TEXT_1037 = NL + "\t\treturn ";
  protected final String TEXT_1038 = " != null && ((";
  protected final String TEXT_1039 = ".Unsettable";
  protected final String TEXT_1040 = ")";
  protected final String TEXT_1041 = ").isSet();";
  protected final String TEXT_1042 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1043 = ");";
  protected final String TEXT_1044 = NL + "\t\treturn (";
  protected final String TEXT_1045 = " & ";
  protected final String TEXT_1046 = "_ESETFLAG) != 0;";
  protected final String TEXT_1047 = NL + "\t\treturn ";
  protected final String TEXT_1048 = "ESet;";
  protected final String TEXT_1049 = NL + "\t\treturn !((";
  protected final String TEXT_1050 = ".Internal)((";
  protected final String TEXT_1051 = ".Internal.Wrapper)get";
  protected final String TEXT_1052 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1053 = ");";
  protected final String TEXT_1054 = NL + "\t\treturn !((";
  protected final String TEXT_1055 = ".Internal)get";
  protected final String TEXT_1056 = "()).isEmpty(";
  protected final String TEXT_1057 = ");";
  protected final String TEXT_1058 = NL + "\t\t";
  protected final String TEXT_1059 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1060 = "' ";
  protected final String TEXT_1061 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1062 = NL + "\t}" + NL;
  protected final String TEXT_1063 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1064 = "(";
  protected final String TEXT_1065 = ") <em>";
  protected final String TEXT_1066 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1067 = "(";
  protected final String TEXT_1068 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1069 = " ";
  protected final String TEXT_1070 = "__EEXPRESSION = \"";
  protected final String TEXT_1071 = "\";";
  protected final String TEXT_1072 = NL;
  protected final String TEXT_1073 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1074 = "(";
  protected final String TEXT_1075 = ") <em>";
  protected final String TEXT_1076 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1077 = "(";
  protected final String TEXT_1078 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1079 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1080 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1081 = ".Internal)";
  protected final String TEXT_1082 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1083 = NL + "\t/**";
  protected final String TEXT_1084 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY10" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1085 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1086 = NL + "\t * ";
  protected final String TEXT_1087 = NL + "\t * @param ";
  protected final String TEXT_1088 = NL + "\t *   ";
  protected final String TEXT_1089 = NL + "\t * @param ";
  protected final String TEXT_1090 = " ";
  protected final String TEXT_1091 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1092 = NL + "\t * @model ";
  protected final String TEXT_1093 = NL + "\t *        ";
  protected final String TEXT_1094 = NL + "\t * @model";
  protected final String TEXT_1095 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1096 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY11" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1097 = NL + "\t";
  protected final String TEXT_1098 = " ";
  protected final String TEXT_1099 = "(";
  protected final String TEXT_1100 = ")";
  protected final String TEXT_1101 = ";" + NL;
  protected final String TEXT_1102 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1103 = NL + "\tpublic ";
  protected final String TEXT_1104 = " ";
  protected final String TEXT_1105 = "(";
  protected final String TEXT_1106 = ")";
  protected final String TEXT_1107 = NL + "\t{";
  protected final String TEXT_1108 = NL + "\t\t";
  protected final String TEXT_1109 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1110 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1111 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1112 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1113 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1114 = "\",";
  protected final String TEXT_1115 = NL + "\t\t\t\t ";
  protected final String TEXT_1116 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1117 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1118 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1119 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1120 = ".";
  protected final String TEXT_1121 = ");";
  protected final String TEXT_1122 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1123 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1124 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1125 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1126 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1127 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1128 = ".";
  protected final String TEXT_1129 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1130 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1131 = "\", ";
  protected final String TEXT_1132 = ".getObjectLabel(this, ";
  protected final String TEXT_1133 = ") }),";
  protected final String TEXT_1134 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1135 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1136 = NL + "\t\t\t";
  protected final String TEXT_1137 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1138 = "new ";
  protected final String TEXT_1139 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1140 = ", ";
  protected final String TEXT_1141 = ")";
  protected final String TEXT_1142 = "null";
  protected final String TEXT_1143 = ");";
  protected final String TEXT_1144 = NL + "\t\t\treturn ";
  protected final String TEXT_1145 = "(";
  protected final String TEXT_1146 = "(";
  protected final String TEXT_1147 = ")";
  protected final String TEXT_1148 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1149 = "new ";
  protected final String TEXT_1150 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1151 = ", ";
  protected final String TEXT_1152 = ")";
  protected final String TEXT_1153 = "null";
  protected final String TEXT_1154 = ")";
  protected final String TEXT_1155 = ").";
  protected final String TEXT_1156 = "()";
  protected final String TEXT_1157 = ";";
  protected final String TEXT_1158 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1159 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1160 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1161 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1162 = NL + "\t}" + NL;
  protected final String TEXT_1163 = NL + "/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY12" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1164 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1165 = NL + "\t@Override";
  protected final String TEXT_1166 = NL + "\tpublic ";
  protected final String TEXT_1167 = " eInverseAdd(";
  protected final String TEXT_1168 = " otherEnd, int featureID, ";
  protected final String TEXT_1169 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1170 = ")" + NL + "\t\t{";
  protected final String TEXT_1171 = NL + "\t\t\tcase ";
  protected final String TEXT_1172 = ":";
  protected final String TEXT_1173 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1174 = "(";
  protected final String TEXT_1175 = ".InternalMapView";
  protected final String TEXT_1176 = ")";
  protected final String TEXT_1177 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1178 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1179 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1180 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1181 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1182 = "((";
  protected final String TEXT_1183 = ")otherEnd, msgs);";
  protected final String TEXT_1184 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1185 = ", msgs);";
  protected final String TEXT_1186 = NL + "\t\t\t\t";
  protected final String TEXT_1187 = " ";
  protected final String TEXT_1188 = " = (";
  protected final String TEXT_1189 = ")eVirtualGet(";
  protected final String TEXT_1190 = ");";
  protected final String TEXT_1191 = NL + "\t\t\t\t";
  protected final String TEXT_1192 = " ";
  protected final String TEXT_1193 = " = ";
  protected final String TEXT_1194 = "basicGet";
  protected final String TEXT_1195 = "();";
  protected final String TEXT_1196 = NL + "\t\t\t\tif (";
  protected final String TEXT_1197 = " != null)";
  protected final String TEXT_1198 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1199 = ")";
  protected final String TEXT_1200 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1201 = ", null, msgs);";
  protected final String TEXT_1202 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1203 = ")";
  protected final String TEXT_1204 = ").eInverseRemove(this, ";
  protected final String TEXT_1205 = ", ";
  protected final String TEXT_1206 = ".class, msgs);";
  protected final String TEXT_1207 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1208 = "((";
  protected final String TEXT_1209 = ")otherEnd, msgs);";
  protected final String TEXT_1210 = NL + "\t\t}";
  protected final String TEXT_1211 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1212 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1213 = NL + "\t}" + NL;
  protected final String TEXT_1214 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY13" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1215 = NL + "\t@Override";
  protected final String TEXT_1216 = NL + "\tpublic ";
  protected final String TEXT_1217 = " eInverseRemove(";
  protected final String TEXT_1218 = " otherEnd, int featureID, ";
  protected final String TEXT_1219 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1220 = ")" + NL + "\t\t{";
  protected final String TEXT_1221 = NL + "\t\t\tcase ";
  protected final String TEXT_1222 = ":";
  protected final String TEXT_1223 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1224 = ")((";
  protected final String TEXT_1225 = ".InternalMapView";
  protected final String TEXT_1226 = ")";
  protected final String TEXT_1227 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1228 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1229 = ")((";
  protected final String TEXT_1230 = ".Internal.Wrapper)";
  protected final String TEXT_1231 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1232 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1233 = ")";
  protected final String TEXT_1234 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1235 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1236 = ", msgs);";
  protected final String TEXT_1237 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1238 = "(msgs);";
  protected final String TEXT_1239 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1240 = "(null, msgs);";
  protected final String TEXT_1241 = NL + "\t\t}";
  protected final String TEXT_1242 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1243 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1244 = NL + "\t}" + NL;
  protected final String TEXT_1245 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY14" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1246 = NL + "\t@Override";
  protected final String TEXT_1247 = NL + "\tpublic ";
  protected final String TEXT_1248 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1249 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1250 = ")" + NL + "\t\t{";
  protected final String TEXT_1251 = NL + "\t\t\tcase ";
  protected final String TEXT_1252 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1253 = ", ";
  protected final String TEXT_1254 = ".class, msgs);";
  protected final String TEXT_1255 = NL + "\t\t}";
  protected final String TEXT_1256 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1257 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1258 = NL + "\t}" + NL;
  protected final String TEXT_1259 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY15" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1260 = NL + "\t@Override";
  protected final String TEXT_1261 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1262 = ")" + NL + "\t\t{";
  protected final String TEXT_1263 = NL + "\t\t\tcase ";
  protected final String TEXT_1264 = ":";
  protected final String TEXT_1265 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1266 = "();";
  protected final String TEXT_1267 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1268 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1269 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1270 = "(";
  protected final String TEXT_1271 = "());";
  protected final String TEXT_1272 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1273 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1274 = "();";
  protected final String TEXT_1275 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1276 = ".InternalMapView";
  protected final String TEXT_1277 = ")";
  protected final String TEXT_1278 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1279 = "();";
  protected final String TEXT_1280 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1281 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1282 = "().map();";
  protected final String TEXT_1283 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1284 = ".Internal.Wrapper)";
  protected final String TEXT_1285 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1286 = "();";
  protected final String TEXT_1287 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1288 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1289 = ".Internal)";
  protected final String TEXT_1290 = "()).getWrapper();";
  protected final String TEXT_1291 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1292 = "();";
  protected final String TEXT_1293 = NL + "\t\t}";
  protected final String TEXT_1294 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1295 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1296 = NL + "\t}" + NL;
  protected final String TEXT_1297 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY16" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1298 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1299 = NL + "\t@Override";
  protected final String TEXT_1300 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1301 = ")" + NL + "\t\t{";
  protected final String TEXT_1302 = NL + "\t\t\tcase ";
  protected final String TEXT_1303 = ":";
  protected final String TEXT_1304 = NL + "\t\t\t\t((";
  protected final String TEXT_1305 = ".Internal)((";
  protected final String TEXT_1306 = ".Internal.Wrapper)";
  protected final String TEXT_1307 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1308 = NL + "\t\t\t\t((";
  protected final String TEXT_1309 = ".Internal)";
  protected final String TEXT_1310 = "()).set(newValue);";
  protected final String TEXT_1311 = NL + "\t\t\t\t((";
  protected final String TEXT_1312 = ".Setting)((";
  protected final String TEXT_1313 = ".InternalMapView";
  protected final String TEXT_1314 = ")";
  protected final String TEXT_1315 = "()).eMap()).set(newValue);";
  protected final String TEXT_1316 = NL + "\t\t\t\t((";
  protected final String TEXT_1317 = ".Setting)";
  protected final String TEXT_1318 = "()).set(newValue);";
  protected final String TEXT_1319 = NL + "\t\t\t\t";
  protected final String TEXT_1320 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1321 = "().addAll((";
  protected final String TEXT_1322 = "<? extends ";
  protected final String TEXT_1323 = ">";
  protected final String TEXT_1324 = ")newValue);";
  protected final String TEXT_1325 = NL + "\t\t\t\tset";
  protected final String TEXT_1326 = "(((";
  protected final String TEXT_1327 = ")newValue).";
  protected final String TEXT_1328 = "());";
  protected final String TEXT_1329 = NL + "\t\t\t\tset";
  protected final String TEXT_1330 = "(";
  protected final String TEXT_1331 = "(";
  protected final String TEXT_1332 = ")";
  protected final String TEXT_1333 = "newValue);";
  protected final String TEXT_1334 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1335 = NL + "\t\t}";
  protected final String TEXT_1336 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1337 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1338 = NL + "\t}" + NL;
  protected final String TEXT_1339 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY17" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1340 = NL + "\t@Override";
  protected final String TEXT_1341 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1342 = ")" + NL + "\t\t{";
  protected final String TEXT_1343 = NL + "\t\t\tcase ";
  protected final String TEXT_1344 = ":";
  protected final String TEXT_1345 = NL + "\t\t\t\t((";
  protected final String TEXT_1346 = ".Internal.Wrapper)";
  protected final String TEXT_1347 = "()).featureMap().clear();";
  protected final String TEXT_1348 = NL + "\t\t\t\t";
  protected final String TEXT_1349 = "().clear();";
  protected final String TEXT_1350 = NL + "\t\t\t\tunset";
  protected final String TEXT_1351 = "();";
  protected final String TEXT_1352 = NL + "\t\t\t\tset";
  protected final String TEXT_1353 = "((";
  protected final String TEXT_1354 = ")null);";
  protected final String TEXT_1355 = NL + "\t\t\t\t";
  protected final String TEXT_1356 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1357 = NL + "\t\t\t\tset";
  protected final String TEXT_1358 = "(Data";
  protected final String TEXT_1359 = ".";
  protected final String TEXT_1360 = ");";
  protected final String TEXT_1361 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1362 = NL + "\t\t}";
  protected final String TEXT_1363 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1364 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1365 = NL + "\t}" + NL;
  protected final String TEXT_1366 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY18" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1367 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1368 = NL + "\t@Override";
  protected final String TEXT_1369 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1370 = ")" + NL + "\t\t{";
  protected final String TEXT_1371 = NL + "\t\t\tcase ";
  protected final String TEXT_1372 = ":";
  protected final String TEXT_1373 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1374 = "();";
  protected final String TEXT_1375 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1376 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1377 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1378 = ".Internal.Wrapper)";
  protected final String TEXT_1379 = "()).featureMap().isEmpty();";
  protected final String TEXT_1380 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1381 = "() != null && !";
  protected final String TEXT_1382 = "().featureMap().isEmpty();";
  protected final String TEXT_1383 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1384 = "() != null && !";
  protected final String TEXT_1385 = "().isEmpty();";
  protected final String TEXT_1386 = NL + "\t\t\t\t";
  protected final String TEXT_1387 = " ";
  protected final String TEXT_1388 = " = (";
  protected final String TEXT_1389 = ")eVirtualGet(";
  protected final String TEXT_1390 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1391 = " != null && !";
  protected final String TEXT_1392 = ".isEmpty();";
  protected final String TEXT_1393 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1394 = "().isEmpty();";
  protected final String TEXT_1395 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1396 = "();";
  protected final String TEXT_1397 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1398 = "() != null;";
  protected final String TEXT_1399 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1400 = ") != null;";
  protected final String TEXT_1401 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1402 = "() != null;";
  protected final String TEXT_1403 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1404 = "() != null;";
  protected final String TEXT_1405 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1406 = ") != null;";
  protected final String TEXT_1407 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1408 = "() != null;";
  protected final String TEXT_1409 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1410 = " & Data";
  protected final String TEXT_1411 = ".";
  protected final String TEXT_1412 = "_EFLAG) != 0) != Data";
  protected final String TEXT_1413 = ".";
  protected final String TEXT_1414 = ";";
  protected final String TEXT_1415 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1416 = " & Data";
  protected final String TEXT_1417 = ".";
  protected final String TEXT_1418 = "_EFLAG) != Data";
  protected final String TEXT_1419 = ".";
  protected final String TEXT_1420 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1421 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1422 = "() != Data";
  protected final String TEXT_1423 = ".";
  protected final String TEXT_1424 = ";";
  protected final String TEXT_1425 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1426 = ", Data";
  protected final String TEXT_1427 = ".";
  protected final String TEXT_1428 = ") != Data";
  protected final String TEXT_1429 = ".";
  protected final String TEXT_1430 = ";";
  protected final String TEXT_1431 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1432 = "() != Data";
  protected final String TEXT_1433 = ".";
  protected final String TEXT_1434 = ";";
  protected final String TEXT_1435 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1436 = ".";
  protected final String TEXT_1437 = " == null ? ";
  protected final String TEXT_1438 = "() != null : !Data";
  protected final String TEXT_1439 = ".";
  protected final String TEXT_1440 = ".equals(";
  protected final String TEXT_1441 = "());";
  protected final String TEXT_1442 = NL + "\t\t\t\t";
  protected final String TEXT_1443 = " ";
  protected final String TEXT_1444 = " = (";
  protected final String TEXT_1445 = ")eVirtualGet(";
  protected final String TEXT_1446 = ", Data";
  protected final String TEXT_1447 = ".";
  protected final String TEXT_1448 = ");" + NL + "\t\t\t\treturn Data";
  protected final String TEXT_1449 = ".";
  protected final String TEXT_1450 = " == null ? ";
  protected final String TEXT_1451 = "() != null : !Data";
  protected final String TEXT_1452 = ".";
  protected final String TEXT_1453 = ".equals(";
  protected final String TEXT_1454 = "());";
  protected final String TEXT_1455 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1456 = ".";
  protected final String TEXT_1457 = " == null ? ";
  protected final String TEXT_1458 = "() != null : !Data";
  protected final String TEXT_1459 = ".";
  protected final String TEXT_1460 = ".equals(";
  protected final String TEXT_1461 = "());";
  protected final String TEXT_1462 = NL + "\t\t}";
  protected final String TEXT_1463 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1464 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1465 = NL + "\t}" + NL;
  protected final String TEXT_1466 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY19" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1467 = NL + "\t@Override";
  protected final String TEXT_1468 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1469 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1470 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1471 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1472 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1473 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1474 = ": return ";
  protected final String TEXT_1475 = ";";
  protected final String TEXT_1476 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1477 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1478 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY20" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1479 = NL + "\t@Override";
  protected final String TEXT_1480 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1481 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1482 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1483 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1484 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1485 = ": return ";
  protected final String TEXT_1486 = ";";
  protected final String TEXT_1487 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1488 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1489 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1490 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1491 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1492 = ": return ";
  protected final String TEXT_1493 = ";";
  protected final String TEXT_1494 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1495 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1496 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY21" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1497 = NL + "\t@Override";
  protected final String TEXT_1498 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1499 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1500 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1501 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1502 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1503 = ": return ";
  protected final String TEXT_1504 = ";";
  protected final String TEXT_1505 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1506 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1507 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1508 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1509 = ": return ";
  protected final String TEXT_1510 = ";";
  protected final String TEXT_1511 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1512 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1513 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1514 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1515 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1516 = ": return ";
  protected final String TEXT_1517 = ";";
  protected final String TEXT_1518 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1519 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1520 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY22" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1521 = NL + "\t@Override";
  protected final String TEXT_1522 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1523 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY23" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1524 = NL + "\t@Override";
  protected final String TEXT_1525 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1526 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1527 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY24" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1528 = NL + "\t@Override";
  protected final String TEXT_1529 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1530 = NL + "\t\t\tcase ";
  protected final String TEXT_1531 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1532 = ";";
  protected final String TEXT_1533 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY25" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1534 = NL + "\t@Override";
  protected final String TEXT_1535 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1536 = NL + "\t\t\tcase ";
  protected final String TEXT_1537 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1538 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1539 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1540 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY26" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1541 = NL + "\t@Override";
  protected final String TEXT_1542 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1543 = "\"unchecked\"";
  protected final String TEXT_1544 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1545 = ")";
  protected final String TEXT_1546 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1547 = " arguments) throws ";
  protected final String TEXT_1548 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1549 = ")" + NL + "\t\t{";
  protected final String TEXT_1550 = NL + "\t\t\tcase ";
  protected final String TEXT_1551 = ":";
  protected final String TEXT_1552 = NL + "\t\t\t\t";
  protected final String TEXT_1553 = "(";
  protected final String TEXT_1554 = "(";
  protected final String TEXT_1555 = "(";
  protected final String TEXT_1556 = ")";
  protected final String TEXT_1557 = "arguments.get(";
  protected final String TEXT_1558 = ")";
  protected final String TEXT_1559 = ").";
  protected final String TEXT_1560 = "()";
  protected final String TEXT_1561 = ", ";
  protected final String TEXT_1562 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1563 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1564 = "new ";
  protected final String TEXT_1565 = "(";
  protected final String TEXT_1566 = "(";
  protected final String TEXT_1567 = "(";
  protected final String TEXT_1568 = "(";
  protected final String TEXT_1569 = ")";
  protected final String TEXT_1570 = "arguments.get(";
  protected final String TEXT_1571 = ")";
  protected final String TEXT_1572 = ").";
  protected final String TEXT_1573 = "()";
  protected final String TEXT_1574 = ", ";
  protected final String TEXT_1575 = ")";
  protected final String TEXT_1576 = ")";
  protected final String TEXT_1577 = ";";
  protected final String TEXT_1578 = NL + "\t\t}";
  protected final String TEXT_1579 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1580 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1581 = NL + "\t}" + NL;
  protected final String TEXT_1582 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY27" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1583 = NL + "\t@Override";
  protected final String TEXT_1584 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());" + NL + "\t\tif (data != null) result.append(data.toString());" + NL + "\t\t" + NL + "\t\treturn result.toString();" + NL + "\t\t}";
  protected final String TEXT_1585 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY28" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1586 = NL + "\t@";
  protected final String TEXT_1587 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY29" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1588 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY30" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY31" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1589 = " getKey()" + NL + "\t{";
  protected final String TEXT_1590 = NL + "\t\treturn new ";
  protected final String TEXT_1591 = "(getTypedKey());";
  protected final String TEXT_1592 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1593 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY32" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1594 = " key)" + NL + "\t{";
  protected final String TEXT_1595 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1596 = "(";
  protected final String TEXT_1597 = ")";
  protected final String TEXT_1598 = "key);";
  protected final String TEXT_1599 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1600 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1601 = ")key).";
  protected final String TEXT_1602 = "());";
  protected final String TEXT_1603 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1604 = ")key);";
  protected final String TEXT_1605 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY33" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1606 = " getValue()" + NL + "\t{";
  protected final String TEXT_1607 = NL + "\t\treturn new ";
  protected final String TEXT_1608 = "(getTypedValue());";
  protected final String TEXT_1609 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1610 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY34" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1611 = " setValue(";
  protected final String TEXT_1612 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1613 = " oldValue = getValue();";
  protected final String TEXT_1614 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1615 = "(";
  protected final String TEXT_1616 = ")";
  protected final String TEXT_1617 = "value);";
  protected final String TEXT_1618 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1619 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1620 = ")value).";
  protected final String TEXT_1621 = "());";
  protected final String TEXT_1622 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1623 = ")value);";
  protected final String TEXT_1624 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY35" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1625 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1626 = NL + "\tpublic ";
  protected final String TEXT_1627 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1628 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1629 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1630 = NL + NL + NL + NL;
  protected final String TEXT_1631 = NL + "// data Class generation " + NL + "protected static  class Data";
  protected final String TEXT_1632 = NL + NL + "{" + NL;
  protected final String TEXT_1633 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_1634 = " copyright = ";
  protected final String TEXT_1635 = ";";
  protected final String TEXT_1636 = NL;
  protected final String TEXT_1637 = NL;
  protected final String TEXT_1638 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_1639 = "() <em>";
  protected final String TEXT_1640 = "</em>}' ";
  protected final String TEXT_1641 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1642 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1643 = NL + "\t@";
  protected final String TEXT_1644 = NL + "\tprotected ";
  protected final String TEXT_1645 = ".Internal.SettingDelegate ";
  protected final String TEXT_1646 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_1647 = ".Internal)";
  protected final String TEXT_1648 = ").getSettingDelegate();" + NL;
  protected final String TEXT_1649 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1650 = "() <em>";
  protected final String TEXT_1651 = "</em>}' ";
  protected final String TEXT_1652 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1653 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1654 = NL + "\t@";
  protected final String TEXT_1655 = NL + "\tprotected ";
  protected final String TEXT_1656 = " ";
  protected final String TEXT_1657 = ";" + NL;
  protected final String TEXT_1658 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_1659 = "() <em>";
  protected final String TEXT_1660 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1661 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1662 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1663 = NL + "\tprotected static final ";
  protected final String TEXT_1664 = "[] ";
  protected final String TEXT_1665 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_1666 = " [0]";
  protected final String TEXT_1667 = ";" + NL;
  protected final String TEXT_1668 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_1669 = "() <em>";
  protected final String TEXT_1670 = "</em>}' ";
  protected final String TEXT_1671 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1672 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1673 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1674 = NL + "\tprotected static final ";
  protected final String TEXT_1675 = " ";
  protected final String TEXT_1676 = "; // TODO The default value literal \"";
  protected final String TEXT_1677 = "\" is not valid.";
  protected final String TEXT_1678 = " = ";
  protected final String TEXT_1679 = ";";
  protected final String TEXT_1680 = NL;
  protected final String TEXT_1681 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1682 = NL + "\t@";
  protected final String TEXT_1683 = NL + "\tprotected int ";
  protected final String TEXT_1684 = " = 0;" + NL;
  protected final String TEXT_1685 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_1686 = "() <em>";
  protected final String TEXT_1687 = "</em>}' ";
  protected final String TEXT_1688 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1689 = "_EFLAG_OFFSET = ";
  protected final String TEXT_1690 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_1691 = "() <em>";
  protected final String TEXT_1692 = "</em>}' ";
  protected final String TEXT_1693 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1694 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_1695 = ".ordinal()";
  protected final String TEXT_1696 = ".VALUES.indexOf(";
  protected final String TEXT_1697 = ")";
  protected final String TEXT_1698 = " << ";
  protected final String TEXT_1699 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_1700 = " ";
  protected final String TEXT_1701 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_1702 = "[] ";
  protected final String TEXT_1703 = "_EFLAG_VALUES = ";
  protected final String TEXT_1704 = ".values()";
  protected final String TEXT_1705 = "(";
  protected final String TEXT_1706 = "[])";
  protected final String TEXT_1707 = ".VALUES.toArray(new ";
  protected final String TEXT_1708 = "[";
  protected final String TEXT_1709 = ".VALUES.size()])";
  protected final String TEXT_1710 = ";" + NL;
  protected final String TEXT_1711 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_1712 = " representing the value of the '{@link #";
  protected final String TEXT_1713 = "() <em>";
  protected final String TEXT_1714 = "</em>}' ";
  protected final String TEXT_1715 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1716 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1717 = "_EFLAG = ";
  protected final String TEXT_1718 = " << ";
  protected final String TEXT_1719 = "_EFLAG_OFFSET";
  protected final String TEXT_1720 = ";" + NL;
  protected final String TEXT_1721 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1722 = "() <em>";
  protected final String TEXT_1723 = "</em>}' ";
  protected final String TEXT_1724 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1725 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1726 = NL + "\t@";
  protected final String TEXT_1727 = NL + "\tprotected ";
  protected final String TEXT_1728 = " ";
  protected final String TEXT_1729 = " = ";
  protected final String TEXT_1730 = ";" + NL;
  protected final String TEXT_1731 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1732 = NL + "\t@";
  protected final String TEXT_1733 = NL + "\tprotected int ";
  protected final String TEXT_1734 = " = 0;" + NL;
  protected final String TEXT_1735 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_1736 = " ";
  protected final String TEXT_1737 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1738 = "_ESETFLAG = 1 << ";
  protected final String TEXT_1739 = ";" + NL;
  protected final String TEXT_1740 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_1741 = " ";
  protected final String TEXT_1742 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1743 = NL + "\t@";
  protected final String TEXT_1744 = NL + "\tprotected boolean ";
  protected final String TEXT_1745 = "ESet;" + NL;
  protected final String TEXT_1746 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_1747 = ".getOperationID(";
  protected final String TEXT_1748 = ") - ";
  protected final String TEXT_1749 = ";" + NL;
  protected final String TEXT_1750 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1751 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Data";
  protected final String TEXT_1752 = "()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1753 = " super(); ";
  protected final String TEXT_1754 = NL + "\t\t";
  protected final String TEXT_1755 = " |= ";
  protected final String TEXT_1756 = "_EFLAG";
  protected final String TEXT_1757 = "_DEFAULT";
  protected final String TEXT_1758 = ";";
  protected final String TEXT_1759 = NL + "\t}" + NL + "\t" + NL + "\t\t";
  protected final String TEXT_1760 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1761 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param {@link ";
  protected final String TEXT_1762 = " }" + NL + "\t * @generated" + NL + "\t */" + NL + "\t//public Data";
  protected final String TEXT_1763 = "(Data";
  protected final String TEXT_1764 = " data)" + NL + "\t//{" + NL + "\t//\tthis();\t\t" + NL + "\t//\t";
  protected final String TEXT_1765 = NL + "\t//\t";
  protected final String TEXT_1766 = " = data.";
  protected final String TEXT_1767 = ";";
  protected final String TEXT_1768 = NL + "\t//\t}" + NL + "\t";
  protected final String TEXT_1769 = NL + "\t";
  protected final String TEXT_1770 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString(){\t" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1771 = "\t\t";
  protected final String TEXT_1772 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1773 = ": \");";
  protected final String TEXT_1774 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1775 = ": \");";
  protected final String TEXT_1776 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1777 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1778 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1779 = NL + "\t\tif (";
  protected final String TEXT_1780 = "(";
  protected final String TEXT_1781 = " & ";
  protected final String TEXT_1782 = "_ESETFLAG) != 0";
  protected final String TEXT_1783 = "ESet";
  protected final String TEXT_1784 = ") result.append((";
  protected final String TEXT_1785 = " & ";
  protected final String TEXT_1786 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1787 = NL + "\t\tif (";
  protected final String TEXT_1788 = "(";
  protected final String TEXT_1789 = " & ";
  protected final String TEXT_1790 = "_ESETFLAG) != 0";
  protected final String TEXT_1791 = "ESet";
  protected final String TEXT_1792 = ") result.append(";
  protected final String TEXT_1793 = "_EFLAG_VALUES[(";
  protected final String TEXT_1794 = " & ";
  protected final String TEXT_1795 = "_EFLAG) >>> ";
  protected final String TEXT_1796 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1797 = NL + "\t\tif (";
  protected final String TEXT_1798 = "(";
  protected final String TEXT_1799 = " & ";
  protected final String TEXT_1800 = "_ESETFLAG) != 0";
  protected final String TEXT_1801 = "ESet";
  protected final String TEXT_1802 = ") result.append(";
  protected final String TEXT_1803 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1804 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1805 = ", ";
  protected final String TEXT_1806 = "));";
  protected final String TEXT_1807 = NL + "\t\tresult.append((";
  protected final String TEXT_1808 = " & ";
  protected final String TEXT_1809 = "_EFLAG) != 0);";
  protected final String TEXT_1810 = NL + "\t\tresult.append(";
  protected final String TEXT_1811 = "_EFLAG_VALUES[(";
  protected final String TEXT_1812 = " & ";
  protected final String TEXT_1813 = "_EFLAG) >>> ";
  protected final String TEXT_1814 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1815 = NL + "\t\tresult.append(";
  protected final String TEXT_1816 = ");";
  protected final String TEXT_1817 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL + "\t";
  protected final String TEXT_1818 = "\t";
  protected final String TEXT_1819 = NL + "}//end data class";
  protected final String TEXT_1820 = NL + "} //";
  protected final String TEXT_1821 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
 * */

    final GenClass genClass = (GenClass)((Object[])argument)[0]; final GenPackage genPackage = genClass.getGenPackage(); final GenModel genModel=genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); final boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    final boolean isGWT = genModel.getRuntimePlatform() == GenRuntimePlatform.GWT;
    final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    final String singleWildcard = isJDK50 ? "<?>" : "";
    final String negativeOffsetCorrection = genClass.hasOffsetCorrection() ? " - " + genClass.getOffsetCorrectionField(null) : "";
    final String positiveOffsetCorrection = genClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(null) : "";
    final String negativeOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " - EOPERATION_OFFSET_CORRECTION" : "";
    final String positiveOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " + EOPERATION_OFFSET_CORRECTION" : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    if (isInterface) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    genModel.markImportLocation(stringBuffer, genPackage);
    if (isImplementation) { 
 genClass.addClassPsuedoImports();}
    stringBuffer.append(TEXT_10);
    if (isInterface) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_12);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_16);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_20);
    }
    }
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_25);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_26);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_28);
    }}
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    stringBuffer.append(TEXT_30);
    //Class/interface.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_32);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_33);
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_37);
    }
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_40);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_44);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_51);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_52);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_53);
    if (isGWT) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_55);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_56);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_57);
    if (isGWT) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_59);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_60);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_61);
    if (isGWT) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_64);
    }
    stringBuffer.append(TEXT_65);
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_70);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_72);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_75);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_79);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_80);
    if (genModel.getRootExtendsClass().contains(genClass.getClassExtends().substring(9)) && !genModel.isReflectiveDelegation()){
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_84);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_85);
    } else {
    stringBuffer.append(TEXT_86);
    }
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_88);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_91);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_92);
    }
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    if (!genModel.isReflectiveDelegation()){
    stringBuffer.append(TEXT_95);
    if (! genModel.getRootExtendsClass().contains(genClass.getClassExtends().substring(9)) ){
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_102);
    }
    stringBuffer.append(TEXT_103);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_104);
    }
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_107);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    stringBuffer.append(TEXT_108);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_109);
    }
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_111);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_112);
    if (!isImplementation) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_115);
    } else {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_118);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_122);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_124);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_125);
    } else {
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_128);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_130);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_134);
    }
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_136);
    }
    stringBuffer.append(TEXT_137);
    if (!isImplementation) {
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_140);
    } else {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_143);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_146);
    }
    stringBuffer.append(TEXT_147);
    if (!isImplementation) {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_149);
    } else {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_151);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_153);
    } else {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_156);
    }
    stringBuffer.append(TEXT_157);
    }
    stringBuffer.append(TEXT_158);
    if (!isImplementation) {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_162);
    } else {
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_170);
    }
    stringBuffer.append(TEXT_171);
    if (!isImplementation) {
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_174);
    } else {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_178);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_181);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_182);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_183);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_184);
    } else {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_186);
    }
    stringBuffer.append(TEXT_187);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_188);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_189);
    } else {
    stringBuffer.append(TEXT_190);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_191);
    }
    stringBuffer.append(TEXT_192);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_193);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_195);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_197);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_199);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_200);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_202);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_203);
    }
    }
    stringBuffer.append(TEXT_204);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_207);
    }
    stringBuffer.append(TEXT_208);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_210);
    }
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_213);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_216);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_218);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_221);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_224);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_227);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_229);
    }}
    stringBuffer.append(TEXT_230);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_231);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_234);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_235);
    }
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_238);
    }
    stringBuffer.append(TEXT_239);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_240);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_241);
    }
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_245);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_246);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_248);
    }
    stringBuffer.append(TEXT_249);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_250);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_251);
    }
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_254);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_257);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_258);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_262);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_264);
    }
    stringBuffer.append(TEXT_265);
    } 
	else if (!genFeature.isVolatile()) {
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_270);
    }
    stringBuffer.append(TEXT_271);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_272);
    if (!genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_273);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_274);
    }else{
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_276);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_280);
    } else {
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_284);
    }
    stringBuffer.append(TEXT_285);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_286);
    } 	  	  
	  else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_295);
    } 	  
	  else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_302);
    }
    stringBuffer.append(TEXT_303);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_306);
    }
    stringBuffer.append(TEXT_307);
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_312);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_316);
    } else {
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_324);
    }
    } else if (!genFeature.isProperty()) {
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_327);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_328);
    } else {
    stringBuffer.append(TEXT_329);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_330);
    }
    }
    } 	
	else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_340);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_344);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_345);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_347);
    } else {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_349);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_352);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_354);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_356);
    } else {
    stringBuffer.append(TEXT_357);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_359);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_360);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_361);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_363);
    }
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_365);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_367);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_369);
    }
    stringBuffer.append(TEXT_370);
    } else {
    stringBuffer.append(TEXT_371);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_372);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_374);
    }
    stringBuffer.append(TEXT_375);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_377);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_379);
    }
    stringBuffer.append(TEXT_380);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_385);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_386);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_387);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_388);
    } else {
    stringBuffer.append(TEXT_389);
    }
    stringBuffer.append(TEXT_390);
    }
    stringBuffer.append(TEXT_391);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_392);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_393);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_396);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_401);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_402);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_403);
    }
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_406);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_408);
    }
    stringBuffer.append(TEXT_409);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_412);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_415);
    } else {
    stringBuffer.append(TEXT_416);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_417);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_420);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_422);
    } else {
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_424);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_426);
    }
    } else {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_429);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_430);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_431);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_437);
     if (! genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && ! genFeature.hasSettingDelegate() && ! genFeature.isContainer() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_438);
    }
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_442);
    stringBuffer.append(TEXT_443);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_447);
    stringBuffer.append(TEXT_448);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_452);
    } else {
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_455);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_456);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_458);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_460);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_464);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_467);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_470);
    }
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_472);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_473);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_478);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_482);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_483);
    } else {
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_485);
    }
    stringBuffer.append(TEXT_486);
    } else {
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_491);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_496);
    }
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_498);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_501);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_504);
    } else {
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_506);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_509);
    }
    } else {
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_512);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_513);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_521);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_524);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_526);
    }
    }
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_528);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_529);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_532);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_534);
    }
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_536);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_537);
    stringBuffer.append(TEXT_538);
     if (!genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && !genFeature.hasSettingDelegate() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_539);
    if (CodegenUtil.getDataClassExtends(genClass) != ""){
    stringBuffer.append(TEXT_540);
    }}
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_543);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_545);
    }
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_547);
    }
    stringBuffer.append(TEXT_548);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_550);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_552);
    }
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_554);
    }
    stringBuffer.append(TEXT_555);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_557);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_559);
    }
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_561);
    }
    stringBuffer.append(TEXT_562);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_576);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_580);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_586);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_591);
    }
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_593);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_595);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_596);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_598);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_604);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_606);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_608);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_612);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_613);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_614);
    }
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_618);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_619);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_622);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_626);
    }
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_629);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_632);
    }
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_634);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_641);
    }
    stringBuffer.append(TEXT_642);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_648);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_654);
    } else {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_662);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_670);
    } else {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_678);
    if (isJDK50) {
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_680);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_682);
    }
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_684);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_687);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_688);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_695);
    } else {
    stringBuffer.append(TEXT_696);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_698);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_700);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_705);
    } else {
    stringBuffer.append(TEXT_706);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_709);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_713);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_719);
    }
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_722);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_725);
    }
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_727);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_731);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_736);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_738);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_739);
    } else {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_741);
    }
    stringBuffer.append(TEXT_742);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_746);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_748);
    stringBuffer.append( genFeature.getEDefault().equals("null") ? "" : "Data"+genClass.getInterfaceName()+".");
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getCapName());
    }else {
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_751);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_753);
    }
    }
    }
    } 
	else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_756);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_758);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_760);
    }
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_762);
    }
    stringBuffer.append(TEXT_763);
    } else {
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_765);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_767);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_769);
    }
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_771);
    }
    stringBuffer.append(TEXT_772);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_773);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_776);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_779);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_780);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_784);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_786);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_789);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_791);
    }
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_793);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_797);
    }
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_799);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_801);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_805);
    }
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_808);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_811);
    }
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_813);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_818);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_820);
    } else {
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_822);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_823);
    } else {
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_825);
    }
    stringBuffer.append(TEXT_826);
    }
    } else {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_829);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_830);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_835);
    stringBuffer.append(TEXT_836);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_838);
    }
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_840);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_843);
    }
    stringBuffer.append(TEXT_844);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_845);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_847);
    } else {
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_849);
    }
    stringBuffer.append(TEXT_850);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_853);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_855);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_857);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_862);
    }
    stringBuffer.append(TEXT_863);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_865);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_867);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_872);
    }
    stringBuffer.append(TEXT_873);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_875);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_877);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_879);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_881);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_883);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_884);
    }
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_886);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_889);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_893);
    }
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_896);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_899);
    }
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_901);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_906);
    }
    stringBuffer.append(TEXT_907);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_912);
    } else {
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_919);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_922);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_925);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_926);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_928);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_932);
    } else {
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_935);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_937);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_940);
    } else {
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_942);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_946);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_948);
    } else {
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_950);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_951);
    } else {
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_953);
    }
    stringBuffer.append(TEXT_954);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_960);
    } else {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_965);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_968);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_971);
    } else {
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_973);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_977);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_984);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_985);
    } else {
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_987);
    }
    stringBuffer.append(TEXT_988);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_991);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_993);
    } else {
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_995);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_997);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1001);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1002);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1010);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1012);
    }
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1014);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1017);
    }
    stringBuffer.append(TEXT_1018);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1019);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1021);
    } else {
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1023);
    }
    stringBuffer.append(TEXT_1024);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1027);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1029);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1031);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1036);
    }
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1041);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1043);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1046);
    } else {
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1048);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1053);
    } else {
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1057);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1061);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1062);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1072);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1082);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(TEXT_1084);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1085);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1091);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1094);
    }}
    stringBuffer.append(TEXT_1095);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1096);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1101);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1102);
    }
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1107);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1121);
    } else {
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1134);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1135);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1137);
    if (size > 0) {
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1141);
    } else {
    stringBuffer.append(TEXT_1142);
    }
    stringBuffer.append(TEXT_1143);
    } else {
    stringBuffer.append(TEXT_1144);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1145);
    }
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1148);
    if (size > 0) {
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1152);
    } else {
    stringBuffer.append(TEXT_1153);
    }
    stringBuffer.append(TEXT_1154);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1156);
    }
    stringBuffer.append(TEXT_1157);
    }
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1160);
    } else {
    stringBuffer.append(TEXT_1161);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1162);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1163);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1164);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1165);
    }
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1170);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1172);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1177);
    } else {
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1179);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1180);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1183);
    } else {
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1185);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1190);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1193);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1195);
    }
    stringBuffer.append(TEXT_1196);
    stringBuffer.append( (genModel.isVirtualDelegation()|| genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1197);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1199);
    stringBuffer.append( (genModel.isVirtualDelegation() || genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1201);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1203);
    stringBuffer.append( (genModel.isVirtualDelegation()|| genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1206);
    }
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1209);
    }
    }
    stringBuffer.append(TEXT_1210);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1211);
    } else {
    stringBuffer.append(TEXT_1212);
    }
    stringBuffer.append(TEXT_1213);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1214);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1215);
    }
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1220);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1222);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1227);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1231);
    } else {
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1234);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1236);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1238);
    } else {
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1240);
    }
    }
    stringBuffer.append(TEXT_1241);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1242);
    } else {
    stringBuffer.append(TEXT_1243);
    }
    stringBuffer.append(TEXT_1244);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1245);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1246);
    }
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1250);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1254);
    }
    stringBuffer.append(TEXT_1255);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1256);
    } else {
    stringBuffer.append(TEXT_1257);
    }
    stringBuffer.append(TEXT_1258);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1259);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1260);
    }
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1262);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1264);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1266);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1268);
    } else {
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1271);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1273);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1274);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1279);
    } else {
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1282);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1286);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1290);
    } else {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1292);
    }
    }
    stringBuffer.append(TEXT_1293);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1294);
    } else {
    stringBuffer.append(TEXT_1295);
    }
    stringBuffer.append(TEXT_1296);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1297);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1298);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1299);
    }
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1301);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1303);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1307);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1310);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1313);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1315);
    } else {
    stringBuffer.append(TEXT_1316);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1318);
    }
    } else {
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1323);
    }
    stringBuffer.append(TEXT_1324);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1328);
    } else {
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1330);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1332);
    }
    stringBuffer.append(TEXT_1333);
    }
    stringBuffer.append(TEXT_1334);
    }
    stringBuffer.append(TEXT_1335);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1336);
    } else {
    stringBuffer.append(TEXT_1337);
    }
    stringBuffer.append(TEXT_1338);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1339);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1340);
    }
    stringBuffer.append(TEXT_1341);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1342);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1344);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1346);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1347);
    } else {
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1349);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1351);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1354);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1355);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1356);
    } else {
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1358);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1360);
    }
    stringBuffer.append(TEXT_1361);
    }
    stringBuffer.append(TEXT_1362);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1363);
    } else {
    stringBuffer.append(TEXT_1364);
    }
    stringBuffer.append(TEXT_1365);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1366);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1367);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1368);
    }
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1370);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1372);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1374);
    } else {
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1376);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1378);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1379);
    } else {
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1382);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1383);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1385);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1388);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1392);
    } else {
    stringBuffer.append(TEXT_1393);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1394);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1395);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1396);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1397);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1398);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1400);
    } else {
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1402);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1404);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1406);
    } else {
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1408);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1414);
    } else {
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1416);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1418);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1420);
    }
    } else {
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1424);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1425);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1430);
    } else {
    stringBuffer.append(TEXT_1431);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1432);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1433);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1434);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1436);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1437);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1438);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1439);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1440);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1441);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1442);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1443);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1444);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1448);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1454);
    } else {
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1458);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1460);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1461);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1462);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1463);
    } else {
    stringBuffer.append(TEXT_1464);
    }
    stringBuffer.append(TEXT_1465);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1466);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1467);
    }
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1469);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1472);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1474);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1475);
    }
    stringBuffer.append(TEXT_1476);
    }
    stringBuffer.append(TEXT_1477);
    }
    stringBuffer.append(TEXT_1478);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1479);
    }
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1481);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1483);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1486);
    }
    stringBuffer.append(TEXT_1487);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1490);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1491);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1492);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1493);
    }
    stringBuffer.append(TEXT_1494);
    }
    stringBuffer.append(TEXT_1495);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1496);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1497);
    }
    stringBuffer.append(TEXT_1498);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1499);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1501);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1504);
    }
    }
    stringBuffer.append(TEXT_1505);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1506);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1507);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1508);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1510);
    }
    stringBuffer.append(TEXT_1511);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1514);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1516);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1517);
    }
    stringBuffer.append(TEXT_1518);
    }
    stringBuffer.append(TEXT_1519);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1520);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1521);
    }
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1523);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1524);
    }
    stringBuffer.append(TEXT_1525);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1526);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1527);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1528);
    }
    stringBuffer.append(TEXT_1529);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1530);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1531);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1532);
    }
    stringBuffer.append(TEXT_1533);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1534);
    }
    stringBuffer.append(TEXT_1535);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1536);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1537);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1538);
    }
    stringBuffer.append(TEXT_1539);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1540);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1541);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1542);
    if (!isRaw) {
    stringBuffer.append(TEXT_1543);
    } else {
    stringBuffer.append(TEXT_1544);
    }
    stringBuffer.append(TEXT_1545);
    }
    }
    stringBuffer.append(TEXT_1546);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1547);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1548);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1549);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1550);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1551);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1552);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1553);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1554);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1555);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1556);
    }
    stringBuffer.append(TEXT_1557);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1558);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1559);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1560);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1561);
    }
    }
    stringBuffer.append(TEXT_1562);
    } else {
    stringBuffer.append(TEXT_1563);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1564);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1565);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1566);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1567);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1568);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1569);
    }
    stringBuffer.append(TEXT_1570);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1571);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1572);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1573);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1574);
    }
    }
    stringBuffer.append(TEXT_1575);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1576);
    }
    stringBuffer.append(TEXT_1577);
    }
    }
    stringBuffer.append(TEXT_1578);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1579);
    } else {
    stringBuffer.append(TEXT_1580);
    }
    stringBuffer.append(TEXT_1581);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1582);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1583);
    }
    stringBuffer.append(TEXT_1584);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1585);
    if (isGWT) {
    stringBuffer.append(TEXT_1586);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1587);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1588);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1589);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1590);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1591);
    } else {
    stringBuffer.append(TEXT_1592);
    }
    stringBuffer.append(TEXT_1593);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1594);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1595);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1596);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1597);
    }
    stringBuffer.append(TEXT_1598);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1599);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1600);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1601);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1602);
    } else {
    stringBuffer.append(TEXT_1603);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1604);
    }
    stringBuffer.append(TEXT_1605);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1606);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1607);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1608);
    } else {
    stringBuffer.append(TEXT_1609);
    }
    stringBuffer.append(TEXT_1610);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1611);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1612);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1613);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1614);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1615);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1616);
    }
    stringBuffer.append(TEXT_1617);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1618);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1619);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1620);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1621);
    } else {
    stringBuffer.append(TEXT_1622);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1623);
    }
    stringBuffer.append(TEXT_1624);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1625);
    }
    stringBuffer.append(TEXT_1626);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1627);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1628);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1629);
    }
    stringBuffer.append(TEXT_1630);
    if (isImplementation && (!(genModel.isReflectiveDelegation()))) {
    stringBuffer.append(TEXT_1631);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(CodegenUtil.getDataClassExtends(genClass));
    stringBuffer.append(TEXT_1632);
     if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_1633);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1634);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_1635);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1636);
    }
    stringBuffer.append(TEXT_1637);
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1638);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1640);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1641);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1642);
    if (isGWT) {
    stringBuffer.append(TEXT_1643);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1644);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1645);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1646);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1647);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1648);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1649);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1651);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1652);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1653);
    if (isGWT) {
    stringBuffer.append(TEXT_1654);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1655);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_1656);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1657);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_1658);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1659);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1660);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1661);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_1662);
    }
    stringBuffer.append(TEXT_1663);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_1664);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1665);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_1667);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1669);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1670);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1671);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1672);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_1673);
    }
    stringBuffer.append(TEXT_1674);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1675);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_1676);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_1677);
    } else {
    stringBuffer.append(TEXT_1678);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_1679);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_1680);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1681);
    if (isGWT) {
    stringBuffer.append(TEXT_1682);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1683);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1684);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_1685);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1686);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1687);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1689);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_1690);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1691);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1692);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1693);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1694);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1695);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1696);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1697);
    }
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1699);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1700);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_1701);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1702);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1703);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1704);
    } else {
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1706);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1707);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1708);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1709);
    }
    stringBuffer.append(TEXT_1710);
    }
    stringBuffer.append(TEXT_1711);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1714);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1715);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1716);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1717);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_1718);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1719);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_1720);
    } else {
    stringBuffer.append(TEXT_1721);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1722);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1723);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1725);
    if (isGWT) {
    stringBuffer.append(TEXT_1726);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1727);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1728);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1730);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1731);
    if (isGWT) {
    stringBuffer.append(TEXT_1732);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1734);
    }
    stringBuffer.append(TEXT_1735);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1736);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1737);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_1739);
    } else {
    stringBuffer.append(TEXT_1740);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1741);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1742);
    if (isGWT) {
    stringBuffer.append(TEXT_1743);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1744);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1745);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1746);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1747);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_1749);
    }
    if (isImplementation ) {
    stringBuffer.append(TEXT_1750);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1751);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1752);
    if ( CodegenUtil.getDataClassExtends(genClass) !=""){
    stringBuffer.append(TEXT_1753);
    }
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_1754);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1755);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1756);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1757);
    }
    stringBuffer.append(TEXT_1758);
    }
    stringBuffer.append(TEXT_1759);
    if ( CodegenUtil.getDataClassExtends(genClass) !=""){
    stringBuffer.append(TEXT_1760);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1761);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
    stringBuffer.append(TEXT_1762);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1763);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
    stringBuffer.append(TEXT_1764);
     for (GenFeature genFeat : genClass.getClassExtendsGenClass().getDeclaredFieldGenFeatures()){
    stringBuffer.append(TEXT_1765);
    stringBuffer.append(genFeat.getSafeName());
    stringBuffer.append(TEXT_1766);
    stringBuffer.append(genFeat.getSafeName());
    stringBuffer.append(TEXT_1767);
    }
    stringBuffer.append(TEXT_1768);
    }
    stringBuffer.append(TEXT_1769);
    if (!genModel.isDynamicDelegation() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_1770);
    { boolean first = true;
    stringBuffer.append(TEXT_1771);
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1772);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1773);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1774);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1775);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1776);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1777);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1778);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1779);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1780);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1781);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1782);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1783);
    }
    stringBuffer.append(TEXT_1784);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1785);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1786);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1787);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1788);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1789);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1790);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1791);
    }
    stringBuffer.append(TEXT_1792);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1793);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1794);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1795);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1796);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1797);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1798);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1799);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1800);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1801);
    }
    stringBuffer.append(TEXT_1802);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1803);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1804);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1805);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1806);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1807);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1808);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1809);
    } else {
    stringBuffer.append(TEXT_1810);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1811);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1812);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1813);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1814);
    }
    } else {
    stringBuffer.append(TEXT_1815);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1816);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1817);
    }
    stringBuffer.append(TEXT_1818);
    }
    stringBuffer.append(TEXT_1819);
    } 
    stringBuffer.append(TEXT_1820);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1821);
    return stringBuffer.toString();
  }
}
