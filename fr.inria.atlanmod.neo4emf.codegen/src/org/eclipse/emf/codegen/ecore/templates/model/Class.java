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
  protected final String TEXT_239 = "()" + NL + "\t{";
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
  protected final String TEXT_308 = NL + "\t\tif ( isLoaded()) " + NL + "\t\t\teNotify(new ENotificationImpl(this, ";
  protected final String TEXT_309 = ".GET, ";
  protected final String TEXT_310 = ", null, null));" + NL + "\t\treturn (";
  protected final String TEXT_311 = ")eVirtualGet(";
  protected final String TEXT_312 = ", Data";
  protected final String TEXT_313 = ".";
  protected final String TEXT_314 = ");";
  protected final String TEXT_315 = NL + "\t\t  if ( isLoaded()) " + NL + "\t\t\teNotify(new ENotificationImpl(this, ";
  protected final String TEXT_316 = ".GET, ";
  protected final String TEXT_317 = ", null, null));" + NL + "\t\treturn (";
  protected final String TEXT_318 = " & Data";
  protected final String TEXT_319 = ".";
  protected final String TEXT_320 = "_EFLAG) != 0;";
  protected final String TEXT_321 = NL + "\t\tif ( isLoaded()) " + NL + "\t\t\teNotify(new ENotificationImpl(this, ";
  protected final String TEXT_322 = ".GET, ";
  protected final String TEXT_323 = ", null, null));" + NL + "\t\treturn Data";
  protected final String TEXT_324 = ".";
  protected final String TEXT_325 = "_EFLAG_VALUES[(";
  protected final String TEXT_326 = " & Data";
  protected final String TEXT_327 = ".";
  protected final String TEXT_328 = "_EFLAG) >>> Data";
  protected final String TEXT_329 = ".";
  protected final String TEXT_330 = "_EFLAG_OFFSET];";
  protected final String TEXT_331 = NL + "\t\tif ( isLoaded()) " + NL + "\t\t\teNotify(new ENotificationImpl(this, ";
  protected final String TEXT_332 = ".GET, ";
  protected final String TEXT_333 = ", null, null));" + NL + "\t\treturn ";
  protected final String TEXT_334 = ";";
  protected final String TEXT_335 = NL + "\t\t";
  protected final String TEXT_336 = " ";
  protected final String TEXT_337 = " = basicGet";
  protected final String TEXT_338 = "();" + NL + "\t\treturn ";
  protected final String TEXT_339 = " != null && ";
  protected final String TEXT_340 = ".eIsProxy() ? ";
  protected final String TEXT_341 = "eResolveProxy((";
  protected final String TEXT_342 = ")";
  protected final String TEXT_343 = ") : ";
  protected final String TEXT_344 = ";";
  protected final String TEXT_345 = NL + "\t\treturn new ";
  protected final String TEXT_346 = "((";
  protected final String TEXT_347 = ".Internal)((";
  protected final String TEXT_348 = ".Internal.Wrapper)get";
  protected final String TEXT_349 = "()).featureMap().";
  protected final String TEXT_350 = "list(";
  protected final String TEXT_351 = "));";
  protected final String TEXT_352 = NL + "\t\treturn (";
  protected final String TEXT_353 = ")get";
  protected final String TEXT_354 = "().";
  protected final String TEXT_355 = "list(";
  protected final String TEXT_356 = ");";
  protected final String TEXT_357 = NL + "\t\treturn ((";
  protected final String TEXT_358 = ".Internal.Wrapper)get";
  protected final String TEXT_359 = "()).featureMap().list(";
  protected final String TEXT_360 = ");";
  protected final String TEXT_361 = NL + "\t\treturn get";
  protected final String TEXT_362 = "().list(";
  protected final String TEXT_363 = ");";
  protected final String TEXT_364 = NL + "\t\treturn ";
  protected final String TEXT_365 = "(";
  protected final String TEXT_366 = "(";
  protected final String TEXT_367 = ")";
  protected final String TEXT_368 = "((";
  protected final String TEXT_369 = ".Internal.Wrapper)get";
  protected final String TEXT_370 = "()).featureMap().get(";
  protected final String TEXT_371 = ", true)";
  protected final String TEXT_372 = ").";
  protected final String TEXT_373 = "()";
  protected final String TEXT_374 = ";";
  protected final String TEXT_375 = NL + "\t\treturn ";
  protected final String TEXT_376 = "(";
  protected final String TEXT_377 = "(";
  protected final String TEXT_378 = ")";
  protected final String TEXT_379 = "get";
  protected final String TEXT_380 = "().get(";
  protected final String TEXT_381 = ", true)";
  protected final String TEXT_382 = ").";
  protected final String TEXT_383 = "()";
  protected final String TEXT_384 = ";";
  protected final String TEXT_385 = NL + "\t\t";
  protected final String TEXT_386 = NL + "\t\t";
  protected final String TEXT_387 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_388 = "' ";
  protected final String TEXT_389 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_390 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_391 = "EcoreEMap";
  protected final String TEXT_392 = "BasicFeatureMap";
  protected final String TEXT_393 = "EcoreEList";
  protected final String TEXT_394 = " should be used.";
  protected final String TEXT_395 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_396 = "\t" + NL + "\t}";
  protected final String TEXT_397 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_398 = NL + "\tpublic ";
  protected final String TEXT_399 = " basicGet";
  protected final String TEXT_400 = "()" + NL + "\t{";
  protected final String TEXT_401 = NL + "\t\treturn (";
  protected final String TEXT_402 = ")eDynamicGet(";
  protected final String TEXT_403 = ", ";
  protected final String TEXT_404 = ", false, ";
  protected final String TEXT_405 = ");";
  protected final String TEXT_406 = NL + "\t\treturn ";
  protected final String TEXT_407 = "(";
  protected final String TEXT_408 = "(";
  protected final String TEXT_409 = ")";
  protected final String TEXT_410 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_411 = ").";
  protected final String TEXT_412 = "()";
  protected final String TEXT_413 = ";";
  protected final String TEXT_414 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_415 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_416 = ")eInternalContainer();";
  protected final String TEXT_417 = NL + "\t\treturn (";
  protected final String TEXT_418 = ")eVirtualGet(";
  protected final String TEXT_419 = ");";
  protected final String TEXT_420 = NL + "\t\treturn data != null ? ";
  protected final String TEXT_421 = " : null;";
  protected final String TEXT_422 = NL + "\t\treturn (";
  protected final String TEXT_423 = ")((";
  protected final String TEXT_424 = ".Internal.Wrapper)get";
  protected final String TEXT_425 = "()).featureMap().get(";
  protected final String TEXT_426 = ", false);";
  protected final String TEXT_427 = NL + "\t\treturn (";
  protected final String TEXT_428 = ")get";
  protected final String TEXT_429 = "().get(";
  protected final String TEXT_430 = ", false);";
  protected final String TEXT_431 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_432 = "' ";
  protected final String TEXT_433 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_434 = NL + "\t}" + NL;
  protected final String TEXT_435 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_436 = NL + "\tpublic ";
  protected final String TEXT_437 = " basicSet";
  protected final String TEXT_438 = "(";
  protected final String TEXT_439 = " new";
  protected final String TEXT_440 = ", ";
  protected final String TEXT_441 = " msgs)" + NL + "\t{" + NL + "\t";
  protected final String TEXT_442 = NL + "\t\t" + NL + "\t";
  protected final String TEXT_443 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_444 = ")new";
  protected final String TEXT_445 = ", ";
  protected final String TEXT_446 = ", msgs);";
  protected final String TEXT_447 = NL + "\t\treturn msgs;";
  protected final String TEXT_448 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_449 = ")new";
  protected final String TEXT_450 = ", ";
  protected final String TEXT_451 = ", msgs);";
  protected final String TEXT_452 = NL + "\t\treturn msgs;";
  protected final String TEXT_453 = NL + "\t\tObject old";
  protected final String TEXT_454 = " = eVirtualSet(";
  protected final String TEXT_455 = ", new";
  protected final String TEXT_456 = ");";
  protected final String TEXT_457 = NL + "\t\t";
  protected final String TEXT_458 = " old";
  protected final String TEXT_459 = " = ";
  protected final String TEXT_460 = ";" + NL + "\t\t";
  protected final String TEXT_461 = " = new";
  protected final String TEXT_462 = ";";
  protected final String TEXT_463 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_464 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_465 = NL + "\t\tboolean old";
  protected final String TEXT_466 = "ESet = (";
  protected final String TEXT_467 = " & ";
  protected final String TEXT_468 = "_ESETFLAG) != 0;";
  protected final String TEXT_469 = NL + "\t\t";
  protected final String TEXT_470 = " |= ";
  protected final String TEXT_471 = "_ESETFLAG;";
  protected final String TEXT_472 = NL + "\t\tboolean old";
  protected final String TEXT_473 = "ESet = ";
  protected final String TEXT_474 = "ESet;";
  protected final String TEXT_475 = NL + "\t\t";
  protected final String TEXT_476 = "ESet = true;";
  protected final String TEXT_477 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_478 = NL + "\t\t\t";
  protected final String TEXT_479 = " notification = new ";
  protected final String TEXT_480 = "(this, ";
  protected final String TEXT_481 = ".SET, ";
  protected final String TEXT_482 = ", ";
  protected final String TEXT_483 = "isSetChange ? null : old";
  protected final String TEXT_484 = "old";
  protected final String TEXT_485 = ", new";
  protected final String TEXT_486 = ", ";
  protected final String TEXT_487 = "isSetChange";
  protected final String TEXT_488 = "!old";
  protected final String TEXT_489 = "ESet";
  protected final String TEXT_490 = ");";
  protected final String TEXT_491 = NL + "\t\t\t";
  protected final String TEXT_492 = " notification = new ";
  protected final String TEXT_493 = "(this, ";
  protected final String TEXT_494 = ".SET, ";
  protected final String TEXT_495 = ", ";
  protected final String TEXT_496 = "old";
  protected final String TEXT_497 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_498 = "old";
  protected final String TEXT_499 = ", new";
  protected final String TEXT_500 = ");";
  protected final String TEXT_501 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_502 = NL + "\t\treturn msgs;";
  protected final String TEXT_503 = NL + "\t\treturn ((";
  protected final String TEXT_504 = ".Internal)((";
  protected final String TEXT_505 = ".Internal.Wrapper)get";
  protected final String TEXT_506 = "()).featureMap()).basicAdd(";
  protected final String TEXT_507 = ", new";
  protected final String TEXT_508 = ", msgs);";
  protected final String TEXT_509 = NL + "\t\treturn ((";
  protected final String TEXT_510 = ".Internal)get";
  protected final String TEXT_511 = "()).basicAdd(";
  protected final String TEXT_512 = ", new";
  protected final String TEXT_513 = ", msgs);";
  protected final String TEXT_514 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_515 = "' ";
  protected final String TEXT_516 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_517 = NL + "\t}" + NL;
  protected final String TEXT_518 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_519 = "#";
  protected final String TEXT_520 = " <em>";
  protected final String TEXT_521 = "</em>}' ";
  protected final String TEXT_522 = ".";
  protected final String TEXT_523 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY1" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_524 = "</em>' ";
  protected final String TEXT_525 = ".";
  protected final String TEXT_526 = NL + "\t * @see ";
  protected final String TEXT_527 = NL + "\t * @see #isSet";
  protected final String TEXT_528 = "()";
  protected final String TEXT_529 = NL + "\t * @see #unset";
  protected final String TEXT_530 = "()";
  protected final String TEXT_531 = NL + "\t * @see #";
  protected final String TEXT_532 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_533 = NL + " /**" + NL + " * <!-- begin-user-doc -->" + NL + " *YY2" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */";
  protected final String TEXT_534 = NL + "\tvoid set";
  protected final String TEXT_535 = "(";
  protected final String TEXT_536 = " value);" + NL;
  protected final String TEXT_537 = NL + "\tpublic void set";
  protected final String TEXT_538 = "_";
  protected final String TEXT_539 = "(";
  protected final String TEXT_540 = " ";
  protected final String TEXT_541 = ")" + NL + "\t{";
  protected final String TEXT_542 = NL + "\t";
  protected final String TEXT_543 = NL + "\t\t";
  protected final String TEXT_544 = NL + "\t";
  protected final String TEXT_545 = NL + "\t\teDynamicSet(";
  protected final String TEXT_546 = ", ";
  protected final String TEXT_547 = ", ";
  protected final String TEXT_548 = "new ";
  protected final String TEXT_549 = "(";
  protected final String TEXT_550 = "new";
  protected final String TEXT_551 = ")";
  protected final String TEXT_552 = ");";
  protected final String TEXT_553 = NL + "\t\teSet(";
  protected final String TEXT_554 = ", ";
  protected final String TEXT_555 = "new ";
  protected final String TEXT_556 = "(";
  protected final String TEXT_557 = "new";
  protected final String TEXT_558 = ")";
  protected final String TEXT_559 = ");";
  protected final String TEXT_560 = NL + "\t\t";
  protected final String TEXT_561 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_562 = "new ";
  protected final String TEXT_563 = "(";
  protected final String TEXT_564 = "new";
  protected final String TEXT_565 = ")";
  protected final String TEXT_566 = ");";
  protected final String TEXT_567 = NL + "\t\tif (new";
  protected final String TEXT_568 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_569 = " && new";
  protected final String TEXT_570 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_571 = ".isAncestor(this, ";
  protected final String TEXT_572 = "new";
  protected final String TEXT_573 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_574 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_575 = NL + "\t\t\t";
  protected final String TEXT_576 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_577 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_578 = ")new";
  protected final String TEXT_579 = ").eInverseAdd(this, ";
  protected final String TEXT_580 = ", ";
  protected final String TEXT_581 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_582 = "(";
  protected final String TEXT_583 = "new";
  protected final String TEXT_584 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_585 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_586 = "(this, ";
  protected final String TEXT_587 = ".SET, ";
  protected final String TEXT_588 = ", new";
  protected final String TEXT_589 = ", new";
  protected final String TEXT_590 = "));";
  protected final String TEXT_591 = NL + "\t\t";
  protected final String TEXT_592 = " ";
  protected final String TEXT_593 = " = (";
  protected final String TEXT_594 = ")eVirtualGet(";
  protected final String TEXT_595 = ");";
  protected final String TEXT_596 = NL + "\t\tif (new";
  protected final String TEXT_597 = " != ";
  protected final String TEXT_598 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_599 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_600 = " != null)";
  protected final String TEXT_601 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_602 = ") ";
  protected final String TEXT_603 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_604 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_605 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_606 = ")new";
  protected final String TEXT_607 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_608 = ", null, msgs);";
  protected final String TEXT_609 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_610 = ") ";
  protected final String TEXT_611 = ").eInverseRemove(this, ";
  protected final String TEXT_612 = ", ";
  protected final String TEXT_613 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_614 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_615 = ")new";
  protected final String TEXT_616 = ").eInverseAdd(this, ";
  protected final String TEXT_617 = ", ";
  protected final String TEXT_618 = ".class, msgs);";
  protected final String TEXT_619 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_620 = "(";
  protected final String TEXT_621 = "new";
  protected final String TEXT_622 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_623 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_624 = NL + "\t\t\tboolean old";
  protected final String TEXT_625 = "ESet = eVirtualIsSet(";
  protected final String TEXT_626 = ");";
  protected final String TEXT_627 = NL + "\t\t\tboolean old";
  protected final String TEXT_628 = "ESet = (";
  protected final String TEXT_629 = " & ";
  protected final String TEXT_630 = "_ESETFLAG) != 0;";
  protected final String TEXT_631 = NL + "\t\t\t";
  protected final String TEXT_632 = " |= ";
  protected final String TEXT_633 = "_ESETFLAG;";
  protected final String TEXT_634 = NL + "\t\t\tboolean old";
  protected final String TEXT_635 = "ESet = ";
  protected final String TEXT_636 = "ESet;";
  protected final String TEXT_637 = NL + "\t\t\t";
  protected final String TEXT_638 = "ESet = true;";
  protected final String TEXT_639 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_640 = "(this, ";
  protected final String TEXT_641 = ".SET, ";
  protected final String TEXT_642 = ", new";
  protected final String TEXT_643 = ", new";
  protected final String TEXT_644 = ", !old";
  protected final String TEXT_645 = "ESet));";
  protected final String TEXT_646 = NL + "\t\t}";
  protected final String TEXT_647 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_648 = "(this, ";
  protected final String TEXT_649 = ".SET, ";
  protected final String TEXT_650 = ", new";
  protected final String TEXT_651 = ", new";
  protected final String TEXT_652 = "));";
  protected final String TEXT_653 = NL + "\t\t";
  protected final String TEXT_654 = " old";
  protected final String TEXT_655 = " = (";
  protected final String TEXT_656 = " & Data";
  protected final String TEXT_657 = ".";
  protected final String TEXT_658 = "_EFLAG) != 0;";
  protected final String TEXT_659 = NL + "\t\t";
  protected final String TEXT_660 = " old";
  protected final String TEXT_661 = " = ";
  protected final String TEXT_662 = "_EFLAG_VALUES[(";
  protected final String TEXT_663 = " & Data";
  protected final String TEXT_664 = ".";
  protected final String TEXT_665 = "_EFLAG) >>> ";
  protected final String TEXT_666 = "_EFLAG_OFFSET];";
  protected final String TEXT_667 = NL + "\t\tif (new";
  protected final String TEXT_668 = ") ";
  protected final String TEXT_669 = " |= Data";
  protected final String TEXT_670 = ".";
  protected final String TEXT_671 = "_EFLAG; else ";
  protected final String TEXT_672 = " &= Data";
  protected final String TEXT_673 = ".";
  protected final String TEXT_674 = "_EFLAG;";
  protected final String TEXT_675 = NL + "\t\tif (new";
  protected final String TEXT_676 = " == null) new";
  protected final String TEXT_677 = " = ";
  protected final String TEXT_678 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_679 = " = ";
  protected final String TEXT_680 = " & Data";
  protected final String TEXT_681 = ".";
  protected final String TEXT_682 = "_EFLAG | ";
  protected final String TEXT_683 = "new";
  protected final String TEXT_684 = ".ordinal()";
  protected final String TEXT_685 = ".VALUES.indexOf(new";
  protected final String TEXT_686 = ")";
  protected final String TEXT_687 = " << ";
  protected final String TEXT_688 = "_EFLAG_OFFSET;";
  protected final String TEXT_689 = NL + "\t\t";
  protected final String TEXT_690 = " old";
  protected final String TEXT_691 = " = ";
  protected final String TEXT_692 = ";";
  protected final String TEXT_693 = NL + "\t\t";
  protected final String TEXT_694 = " ";
  protected final String TEXT_695 = " = new";
  protected final String TEXT_696 = " == null ? Data";
  protected final String TEXT_697 = ".";
  protected final String TEXT_698 = " : new";
  protected final String TEXT_699 = ";";
  protected final String TEXT_700 = NL + "\t\t";
  protected final String TEXT_701 = " = new";
  protected final String TEXT_702 = " == null ? ";
  protected final String TEXT_703 = " : new";
  protected final String TEXT_704 = ";";
  protected final String TEXT_705 = NL + "\t\t";
  protected final String TEXT_706 = " ";
  protected final String TEXT_707 = " = ";
  protected final String TEXT_708 = "new";
  protected final String TEXT_709 = ";";
  protected final String TEXT_710 = NL + "\t\t";
  protected final String TEXT_711 = " = ";
  protected final String TEXT_712 = "new";
  protected final String TEXT_713 = ";";
  protected final String TEXT_714 = NL + "\t\tObject old";
  protected final String TEXT_715 = " = eVirtualSet(";
  protected final String TEXT_716 = ", ";
  protected final String TEXT_717 = ");";
  protected final String TEXT_718 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_719 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_720 = NL + "\t\tboolean old";
  protected final String TEXT_721 = "ESet = (";
  protected final String TEXT_722 = " & ";
  protected final String TEXT_723 = "_ESETFLAG) != 0;";
  protected final String TEXT_724 = NL + "\t\t";
  protected final String TEXT_725 = " |= ";
  protected final String TEXT_726 = "_ESETFLAG;";
  protected final String TEXT_727 = NL + "\t\tboolean old";
  protected final String TEXT_728 = "ESet = ";
  protected final String TEXT_729 = "ESet;";
  protected final String TEXT_730 = NL + "\t\t";
  protected final String TEXT_731 = "ESet = true;";
  protected final String TEXT_732 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_733 = NL + "\t\t\t(this, ";
  protected final String TEXT_734 = ".SET," + NL + "\t\t\t";
  protected final String TEXT_735 = ", " + NL + "\t\t\t";
  protected final String TEXT_736 = "isSetChange ? Data";
  protected final String TEXT_737 = ".";
  protected final String TEXT_738 = " : old";
  protected final String TEXT_739 = "old";
  protected final String TEXT_740 = ", ";
  protected final String TEXT_741 = "new";
  protected final String TEXT_742 = ", ";
  protected final String TEXT_743 = "isSetChange";
  protected final String TEXT_744 = "!old";
  protected final String TEXT_745 = "ESet";
  protected final String TEXT_746 = "));";
  protected final String TEXT_747 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_748 = "(" + NL + "\t\t\tthis, ";
  protected final String TEXT_749 = ".SET," + NL + "\t\t\t";
  protected final String TEXT_750 = "," + NL + "\t\t\t";
  protected final String TEXT_751 = NL + "\t\t\t\told";
  protected final String TEXT_752 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_753 = " : old";
  protected final String TEXT_754 = "old";
  protected final String TEXT_755 = ", ";
  protected final String TEXT_756 = "new";
  protected final String TEXT_757 = "));";
  protected final String TEXT_758 = NL + "\t\t((";
  protected final String TEXT_759 = ".Internal)((";
  protected final String TEXT_760 = ".Internal.Wrapper)get";
  protected final String TEXT_761 = "()).featureMap()).set(";
  protected final String TEXT_762 = ", ";
  protected final String TEXT_763 = "new ";
  protected final String TEXT_764 = "(";
  protected final String TEXT_765 = "new";
  protected final String TEXT_766 = ")";
  protected final String TEXT_767 = ");";
  protected final String TEXT_768 = NL + "\t\t((";
  protected final String TEXT_769 = ".Internal)get";
  protected final String TEXT_770 = "()).set(";
  protected final String TEXT_771 = ", ";
  protected final String TEXT_772 = "new ";
  protected final String TEXT_773 = "(";
  protected final String TEXT_774 = "new";
  protected final String TEXT_775 = ")";
  protected final String TEXT_776 = ");";
  protected final String TEXT_777 = NL + "\t\t";
  protected final String TEXT_778 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_779 = "' ";
  protected final String TEXT_780 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_781 = NL + "\t}";
  protected final String TEXT_782 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY3" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_783 = NL + "\tpublic ";
  protected final String TEXT_784 = " basicUnset";
  protected final String TEXT_785 = "(";
  protected final String TEXT_786 = " msgs)" + NL + "\t{";
  protected final String TEXT_787 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_788 = ")";
  protected final String TEXT_789 = "basicGet";
  protected final String TEXT_790 = "(), ";
  protected final String TEXT_791 = ", msgs);";
  protected final String TEXT_792 = "Object old";
  protected final String TEXT_793 = " = ";
  protected final String TEXT_794 = "eVirtualUnset(";
  protected final String TEXT_795 = ");";
  protected final String TEXT_796 = NL + "\t\t";
  protected final String TEXT_797 = " old";
  protected final String TEXT_798 = " = ";
  protected final String TEXT_799 = ";";
  protected final String TEXT_800 = NL + "\t\t";
  protected final String TEXT_801 = " = null;";
  protected final String TEXT_802 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_803 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_804 = NL + "\t\tboolean old";
  protected final String TEXT_805 = "ESet = (";
  protected final String TEXT_806 = " & ";
  protected final String TEXT_807 = "_ESETFLAG) != 0;";
  protected final String TEXT_808 = NL + "\t\t";
  protected final String TEXT_809 = " &= ~";
  protected final String TEXT_810 = "_ESETFLAG;";
  protected final String TEXT_811 = NL + "\t\tboolean old";
  protected final String TEXT_812 = "ESet = ";
  protected final String TEXT_813 = "ESet;";
  protected final String TEXT_814 = NL + "\t\t";
  protected final String TEXT_815 = "ESet = false;";
  protected final String TEXT_816 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_817 = " notification = new ";
  protected final String TEXT_818 = "(this, ";
  protected final String TEXT_819 = ".UNSET, ";
  protected final String TEXT_820 = ", ";
  protected final String TEXT_821 = "isSetChange ? old";
  protected final String TEXT_822 = " : null";
  protected final String TEXT_823 = "old";
  protected final String TEXT_824 = ", null, ";
  protected final String TEXT_825 = "isSetChange";
  protected final String TEXT_826 = "old";
  protected final String TEXT_827 = "ESet";
  protected final String TEXT_828 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_829 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_830 = "' ";
  protected final String TEXT_831 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_832 = NL + "\t}" + NL;
  protected final String TEXT_833 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_834 = "#";
  protected final String TEXT_835 = " <em>";
  protected final String TEXT_836 = "</em>}' ";
  protected final String TEXT_837 = ".";
  protected final String TEXT_838 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY4" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_839 = NL + "\t * @see #isSet";
  protected final String TEXT_840 = "()";
  protected final String TEXT_841 = NL + "\t * @see #";
  protected final String TEXT_842 = "()";
  protected final String TEXT_843 = NL + "\t * @see #set";
  protected final String TEXT_844 = "(";
  protected final String TEXT_845 = ")";
  protected final String TEXT_846 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_847 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY5" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_848 = NL + "\tvoid unset";
  protected final String TEXT_849 = "();" + NL;
  protected final String TEXT_850 = NL + "\tpublic void unset";
  protected final String TEXT_851 = "_";
  protected final String TEXT_852 = "()" + NL + "\t{";
  protected final String TEXT_853 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_854 = ", ";
  protected final String TEXT_855 = ");";
  protected final String TEXT_856 = NL + "\t\teUnset(";
  protected final String TEXT_857 = ");";
  protected final String TEXT_858 = NL + "\t\t";
  protected final String TEXT_859 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_860 = NL + "\t\t";
  protected final String TEXT_861 = " ";
  protected final String TEXT_862 = " = (";
  protected final String TEXT_863 = ")eVirtualGet(";
  protected final String TEXT_864 = ");";
  protected final String TEXT_865 = NL + "\t\tif (";
  protected final String TEXT_866 = " != null) ((";
  protected final String TEXT_867 = ".Unsettable";
  protected final String TEXT_868 = ")";
  protected final String TEXT_869 = ").unset();";
  protected final String TEXT_870 = NL + "\t\t";
  protected final String TEXT_871 = " ";
  protected final String TEXT_872 = " = (";
  protected final String TEXT_873 = ")eVirtualGet(";
  protected final String TEXT_874 = ");";
  protected final String TEXT_875 = NL + "\t\tif (";
  protected final String TEXT_876 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_877 = " msgs = null;";
  protected final String TEXT_878 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_879 = ")";
  protected final String TEXT_880 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_881 = ", null, msgs);";
  protected final String TEXT_882 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_883 = ")";
  protected final String TEXT_884 = ").eInverseRemove(this, ";
  protected final String TEXT_885 = ", ";
  protected final String TEXT_886 = ".class, msgs);";
  protected final String TEXT_887 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_888 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_889 = NL + "\t\t\tboolean old";
  protected final String TEXT_890 = "ESet = eVirtualIsSet(";
  protected final String TEXT_891 = ");";
  protected final String TEXT_892 = NL + "\t\t\tboolean old";
  protected final String TEXT_893 = "ESet = (";
  protected final String TEXT_894 = " & ";
  protected final String TEXT_895 = "_ESETFLAG) != 0;";
  protected final String TEXT_896 = NL + "\t\t\t";
  protected final String TEXT_897 = " &= ~";
  protected final String TEXT_898 = "_ESETFLAG;";
  protected final String TEXT_899 = NL + "\t\t\tboolean old";
  protected final String TEXT_900 = "ESet = ";
  protected final String TEXT_901 = "ESet;";
  protected final String TEXT_902 = NL + "\t\t\t";
  protected final String TEXT_903 = "ESet = false;";
  protected final String TEXT_904 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_905 = "(this, ";
  protected final String TEXT_906 = ".UNSET, ";
  protected final String TEXT_907 = ", null, null, old";
  protected final String TEXT_908 = "ESet));";
  protected final String TEXT_909 = NL + "\t\t}";
  protected final String TEXT_910 = NL + "\t\t";
  protected final String TEXT_911 = " old";
  protected final String TEXT_912 = " = (";
  protected final String TEXT_913 = " & ";
  protected final String TEXT_914 = "_EFLAG) != 0;";
  protected final String TEXT_915 = NL + "\t\t";
  protected final String TEXT_916 = " old";
  protected final String TEXT_917 = " = ";
  protected final String TEXT_918 = "_EFLAG_VALUES[(";
  protected final String TEXT_919 = " & ";
  protected final String TEXT_920 = "_EFLAG) >>> ";
  protected final String TEXT_921 = "_EFLAG_OFFSET];";
  protected final String TEXT_922 = NL + "\t\tObject old";
  protected final String TEXT_923 = " = eVirtualUnset(";
  protected final String TEXT_924 = ");";
  protected final String TEXT_925 = NL + "\t\t";
  protected final String TEXT_926 = " old";
  protected final String TEXT_927 = " = ";
  protected final String TEXT_928 = ";";
  protected final String TEXT_929 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_930 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_931 = NL + "\t\tboolean old";
  protected final String TEXT_932 = "ESet = (";
  protected final String TEXT_933 = " & ";
  protected final String TEXT_934 = "_ESETFLAG) != 0;";
  protected final String TEXT_935 = NL + "\t\tboolean old";
  protected final String TEXT_936 = "ESet = ";
  protected final String TEXT_937 = "ESet;";
  protected final String TEXT_938 = NL + "\t\t";
  protected final String TEXT_939 = " = null;";
  protected final String TEXT_940 = NL + "\t\t";
  protected final String TEXT_941 = " &= ~";
  protected final String TEXT_942 = "_ESETFLAG;";
  protected final String TEXT_943 = NL + "\t\t";
  protected final String TEXT_944 = "ESet = false;";
  protected final String TEXT_945 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_946 = "(this, ";
  protected final String TEXT_947 = ".UNSET, ";
  protected final String TEXT_948 = ", ";
  protected final String TEXT_949 = "isSetChange ? old";
  protected final String TEXT_950 = " : null";
  protected final String TEXT_951 = "old";
  protected final String TEXT_952 = ", null, ";
  protected final String TEXT_953 = "isSetChange";
  protected final String TEXT_954 = "old";
  protected final String TEXT_955 = "ESet";
  protected final String TEXT_956 = "));";
  protected final String TEXT_957 = NL + "\t\tif (";
  protected final String TEXT_958 = ") ";
  protected final String TEXT_959 = " |= ";
  protected final String TEXT_960 = "_EFLAG; else ";
  protected final String TEXT_961 = " &= ~";
  protected final String TEXT_962 = "_EFLAG;";
  protected final String TEXT_963 = NL + "\t\t";
  protected final String TEXT_964 = " = ";
  protected final String TEXT_965 = " & ~";
  protected final String TEXT_966 = "_EFLAG | ";
  protected final String TEXT_967 = "_EFLAG_DEFAULT;";
  protected final String TEXT_968 = NL + "\t\t";
  protected final String TEXT_969 = " = ";
  protected final String TEXT_970 = ";";
  protected final String TEXT_971 = NL + "\t\t";
  protected final String TEXT_972 = " &= ~";
  protected final String TEXT_973 = "_ESETFLAG;";
  protected final String TEXT_974 = NL + "\t\t";
  protected final String TEXT_975 = "ESet = false;";
  protected final String TEXT_976 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_977 = "(this, ";
  protected final String TEXT_978 = ".UNSET, ";
  protected final String TEXT_979 = ", ";
  protected final String TEXT_980 = "isSetChange ? old";
  protected final String TEXT_981 = " : Data";
  protected final String TEXT_982 = ".";
  protected final String TEXT_983 = "old";
  protected final String TEXT_984 = ", Data";
  protected final String TEXT_985 = ".";
  protected final String TEXT_986 = ", ";
  protected final String TEXT_987 = "isSetChange";
  protected final String TEXT_988 = "old";
  protected final String TEXT_989 = "ESet";
  protected final String TEXT_990 = "));";
  protected final String TEXT_991 = NL + "\t\t((";
  protected final String TEXT_992 = ".Internal)((";
  protected final String TEXT_993 = ".Internal.Wrapper)get";
  protected final String TEXT_994 = "()).featureMap()).clear(";
  protected final String TEXT_995 = ");";
  protected final String TEXT_996 = NL + "\t\t((";
  protected final String TEXT_997 = ".Internal)get";
  protected final String TEXT_998 = "()).clear(";
  protected final String TEXT_999 = ");";
  protected final String TEXT_1000 = NL + "\t\t";
  protected final String TEXT_1001 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1002 = "' ";
  protected final String TEXT_1003 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1004 = NL + "\t}" + NL;
  protected final String TEXT_1005 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1006 = "#";
  protected final String TEXT_1007 = " <em>";
  protected final String TEXT_1008 = "</em>}' ";
  protected final String TEXT_1009 = " is set.";
  protected final String TEXT_1010 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY6" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1011 = "</em>' ";
  protected final String TEXT_1012 = " is set.";
  protected final String TEXT_1013 = NL + "\t * @see #unset";
  protected final String TEXT_1014 = "()";
  protected final String TEXT_1015 = NL + "\t * @see #";
  protected final String TEXT_1016 = "()";
  protected final String TEXT_1017 = NL + "\t * @see #set";
  protected final String TEXT_1018 = "(";
  protected final String TEXT_1019 = ")";
  protected final String TEXT_1020 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1021 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY7" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1022 = NL + "\tboolean isSet";
  protected final String TEXT_1023 = "();" + NL;
  protected final String TEXT_1024 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1025 = "_";
  protected final String TEXT_1026 = "()" + NL + "\t{";
  protected final String TEXT_1027 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1028 = ", ";
  protected final String TEXT_1029 = ");";
  protected final String TEXT_1030 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1031 = ");";
  protected final String TEXT_1032 = NL + "\t\treturn ";
  protected final String TEXT_1033 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1034 = NL + "\t\t";
  protected final String TEXT_1035 = " ";
  protected final String TEXT_1036 = " = (";
  protected final String TEXT_1037 = ")eVirtualGet(";
  protected final String TEXT_1038 = ");";
  protected final String TEXT_1039 = NL + "\t\treturn ";
  protected final String TEXT_1040 = " != null && ((";
  protected final String TEXT_1041 = ".Unsettable";
  protected final String TEXT_1042 = ")";
  protected final String TEXT_1043 = ").isSet();";
  protected final String TEXT_1044 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1045 = ");";
  protected final String TEXT_1046 = NL + "\t\treturn (";
  protected final String TEXT_1047 = " & ";
  protected final String TEXT_1048 = "_ESETFLAG) != 0;";
  protected final String TEXT_1049 = NL + "\t\treturn ";
  protected final String TEXT_1050 = "ESet;";
  protected final String TEXT_1051 = NL + "\t\treturn !((";
  protected final String TEXT_1052 = ".Internal)((";
  protected final String TEXT_1053 = ".Internal.Wrapper)get";
  protected final String TEXT_1054 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1055 = ");";
  protected final String TEXT_1056 = NL + "\t\treturn !((";
  protected final String TEXT_1057 = ".Internal)get";
  protected final String TEXT_1058 = "()).isEmpty(";
  protected final String TEXT_1059 = ");";
  protected final String TEXT_1060 = NL + "\t\t";
  protected final String TEXT_1061 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1062 = "' ";
  protected final String TEXT_1063 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1064 = NL + "\t}" + NL;
  protected final String TEXT_1065 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1066 = "(";
  protected final String TEXT_1067 = ") <em>";
  protected final String TEXT_1068 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1069 = "(";
  protected final String TEXT_1070 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1071 = " ";
  protected final String TEXT_1072 = "__EEXPRESSION = \"";
  protected final String TEXT_1073 = "\";";
  protected final String TEXT_1074 = NL;
  protected final String TEXT_1075 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1076 = "(";
  protected final String TEXT_1077 = ") <em>";
  protected final String TEXT_1078 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1079 = "(";
  protected final String TEXT_1080 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1081 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1082 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1083 = ".Internal)";
  protected final String TEXT_1084 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1085 = NL + "\t/**";
  protected final String TEXT_1086 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY10" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1087 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1088 = NL + "\t * ";
  protected final String TEXT_1089 = NL + "\t * @param ";
  protected final String TEXT_1090 = NL + "\t *   ";
  protected final String TEXT_1091 = NL + "\t * @param ";
  protected final String TEXT_1092 = " ";
  protected final String TEXT_1093 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1094 = NL + "\t * @model ";
  protected final String TEXT_1095 = NL + "\t *        ";
  protected final String TEXT_1096 = NL + "\t * @model";
  protected final String TEXT_1097 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1098 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY11" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1099 = NL + "\t";
  protected final String TEXT_1100 = " ";
  protected final String TEXT_1101 = "(";
  protected final String TEXT_1102 = ")";
  protected final String TEXT_1103 = ";" + NL;
  protected final String TEXT_1104 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1105 = NL + "\tpublic ";
  protected final String TEXT_1106 = " ";
  protected final String TEXT_1107 = "(";
  protected final String TEXT_1108 = ")";
  protected final String TEXT_1109 = NL + "\t{";
  protected final String TEXT_1110 = NL + "\t\t";
  protected final String TEXT_1111 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1112 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1113 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1114 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1115 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1116 = "\",";
  protected final String TEXT_1117 = NL + "\t\t\t\t ";
  protected final String TEXT_1118 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1119 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1120 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1121 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1122 = ".";
  protected final String TEXT_1123 = ");";
  protected final String TEXT_1124 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1125 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1126 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1127 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1128 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1129 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1130 = ".";
  protected final String TEXT_1131 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1132 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1133 = "\", ";
  protected final String TEXT_1134 = ".getObjectLabel(this, ";
  protected final String TEXT_1135 = ") }),";
  protected final String TEXT_1136 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1137 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1138 = NL + "\t\t\t";
  protected final String TEXT_1139 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1140 = "new ";
  protected final String TEXT_1141 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1142 = ", ";
  protected final String TEXT_1143 = ")";
  protected final String TEXT_1144 = "null";
  protected final String TEXT_1145 = ");";
  protected final String TEXT_1146 = NL + "\t\t\treturn ";
  protected final String TEXT_1147 = "(";
  protected final String TEXT_1148 = "(";
  protected final String TEXT_1149 = ")";
  protected final String TEXT_1150 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1151 = "new ";
  protected final String TEXT_1152 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1153 = ", ";
  protected final String TEXT_1154 = ")";
  protected final String TEXT_1155 = "null";
  protected final String TEXT_1156 = ")";
  protected final String TEXT_1157 = ").";
  protected final String TEXT_1158 = "()";
  protected final String TEXT_1159 = ";";
  protected final String TEXT_1160 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1161 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1162 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1163 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1164 = NL + "\t}" + NL;
  protected final String TEXT_1165 = NL + "/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY12" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1166 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1167 = NL + "\t@Override";
  protected final String TEXT_1168 = NL + "\tpublic ";
  protected final String TEXT_1169 = " eInverseAdd(";
  protected final String TEXT_1170 = " otherEnd, int featureID, ";
  protected final String TEXT_1171 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1172 = ")" + NL + "\t\t{";
  protected final String TEXT_1173 = NL + "\t\t\tcase ";
  protected final String TEXT_1174 = ":";
  protected final String TEXT_1175 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1176 = "(";
  protected final String TEXT_1177 = ".InternalMapView";
  protected final String TEXT_1178 = ")";
  protected final String TEXT_1179 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1180 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1181 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1182 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1183 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1184 = "((";
  protected final String TEXT_1185 = ")otherEnd, msgs);";
  protected final String TEXT_1186 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1187 = ", msgs);";
  protected final String TEXT_1188 = NL + "\t\t\t\t";
  protected final String TEXT_1189 = " ";
  protected final String TEXT_1190 = " = (";
  protected final String TEXT_1191 = ")eVirtualGet(";
  protected final String TEXT_1192 = ");";
  protected final String TEXT_1193 = NL + "\t\t\t\t";
  protected final String TEXT_1194 = " ";
  protected final String TEXT_1195 = " = ";
  protected final String TEXT_1196 = "basicGet";
  protected final String TEXT_1197 = "();";
  protected final String TEXT_1198 = NL + "\t\t\t\tif (";
  protected final String TEXT_1199 = " != null)";
  protected final String TEXT_1200 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1201 = ")";
  protected final String TEXT_1202 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1203 = ", null, msgs);";
  protected final String TEXT_1204 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1205 = ")";
  protected final String TEXT_1206 = ").eInverseRemove(this, ";
  protected final String TEXT_1207 = ", ";
  protected final String TEXT_1208 = ".class, msgs);";
  protected final String TEXT_1209 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1210 = "((";
  protected final String TEXT_1211 = ")otherEnd, msgs);";
  protected final String TEXT_1212 = NL + "\t\t}";
  protected final String TEXT_1213 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1214 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1215 = NL + "\t}" + NL;
  protected final String TEXT_1216 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY13" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1217 = NL + "\t@Override";
  protected final String TEXT_1218 = NL + "\tpublic ";
  protected final String TEXT_1219 = " eInverseRemove(";
  protected final String TEXT_1220 = " otherEnd, int featureID, ";
  protected final String TEXT_1221 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1222 = ")" + NL + "\t\t{";
  protected final String TEXT_1223 = NL + "\t\t\tcase ";
  protected final String TEXT_1224 = ":";
  protected final String TEXT_1225 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1226 = ")((";
  protected final String TEXT_1227 = ".InternalMapView";
  protected final String TEXT_1228 = ")";
  protected final String TEXT_1229 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1230 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1231 = ")((";
  protected final String TEXT_1232 = ".Internal.Wrapper)";
  protected final String TEXT_1233 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1234 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1235 = ")";
  protected final String TEXT_1236 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1237 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1238 = ", msgs);";
  protected final String TEXT_1239 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1240 = "(msgs);";
  protected final String TEXT_1241 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1242 = "(null, msgs);";
  protected final String TEXT_1243 = NL + "\t\t}";
  protected final String TEXT_1244 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1245 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1246 = NL + "\t}" + NL;
  protected final String TEXT_1247 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY14" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1248 = NL + "\t@Override";
  protected final String TEXT_1249 = NL + "\tpublic ";
  protected final String TEXT_1250 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1251 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1252 = ")" + NL + "\t\t{";
  protected final String TEXT_1253 = NL + "\t\t\tcase ";
  protected final String TEXT_1254 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1255 = ", ";
  protected final String TEXT_1256 = ".class, msgs);";
  protected final String TEXT_1257 = NL + "\t\t}";
  protected final String TEXT_1258 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1259 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1260 = NL + "\t}" + NL;
  protected final String TEXT_1261 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY15" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1262 = NL + "\t@Override";
  protected final String TEXT_1263 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1264 = ")" + NL + "\t\t{";
  protected final String TEXT_1265 = NL + "\t\t\tcase ";
  protected final String TEXT_1266 = ":";
  protected final String TEXT_1267 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1268 = "();";
  protected final String TEXT_1269 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1270 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1271 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1272 = "(";
  protected final String TEXT_1273 = "());";
  protected final String TEXT_1274 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1275 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1276 = "();";
  protected final String TEXT_1277 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1278 = ".InternalMapView";
  protected final String TEXT_1279 = ")";
  protected final String TEXT_1280 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1281 = "();";
  protected final String TEXT_1282 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1283 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1284 = "().map();";
  protected final String TEXT_1285 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1286 = ".Internal.Wrapper)";
  protected final String TEXT_1287 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1288 = "();";
  protected final String TEXT_1289 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1290 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1291 = ".Internal)";
  protected final String TEXT_1292 = "()).getWrapper();";
  protected final String TEXT_1293 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1294 = "();";
  protected final String TEXT_1295 = NL + "\t\t}";
  protected final String TEXT_1296 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1297 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1298 = NL + "\t}" + NL;
  protected final String TEXT_1299 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY16" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1300 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1301 = NL + "\t@Override";
  protected final String TEXT_1302 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1303 = ")" + NL + "\t\t{";
  protected final String TEXT_1304 = NL + "\t\t\tcase ";
  protected final String TEXT_1305 = ":";
  protected final String TEXT_1306 = NL + "\t\t\t\t((";
  protected final String TEXT_1307 = ".Internal)((";
  protected final String TEXT_1308 = ".Internal.Wrapper)";
  protected final String TEXT_1309 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1310 = NL + "\t\t\t\t((";
  protected final String TEXT_1311 = ".Internal)";
  protected final String TEXT_1312 = "()).set(newValue);";
  protected final String TEXT_1313 = NL + "\t\t\t\t((";
  protected final String TEXT_1314 = ".Setting)((";
  protected final String TEXT_1315 = ".InternalMapView";
  protected final String TEXT_1316 = ")";
  protected final String TEXT_1317 = "()).eMap()).set(newValue);";
  protected final String TEXT_1318 = NL + "\t\t\t\t((";
  protected final String TEXT_1319 = ".Setting)";
  protected final String TEXT_1320 = "()).set(newValue);";
  protected final String TEXT_1321 = NL + "\t\t\t\t";
  protected final String TEXT_1322 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1323 = "().addAll((";
  protected final String TEXT_1324 = "<? extends ";
  protected final String TEXT_1325 = ">";
  protected final String TEXT_1326 = ")newValue);";
  protected final String TEXT_1327 = NL + "\t\t\t\tset";
  protected final String TEXT_1328 = "(((";
  protected final String TEXT_1329 = ")newValue).";
  protected final String TEXT_1330 = "());";
  protected final String TEXT_1331 = NL + "\t\t\t\tset";
  protected final String TEXT_1332 = "(";
  protected final String TEXT_1333 = "(";
  protected final String TEXT_1334 = ")";
  protected final String TEXT_1335 = "newValue);";
  protected final String TEXT_1336 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1337 = NL + "\t\t}";
  protected final String TEXT_1338 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1339 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1340 = NL + "\t}" + NL;
  protected final String TEXT_1341 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY17" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1342 = NL + "\t@Override";
  protected final String TEXT_1343 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1344 = ")" + NL + "\t\t{";
  protected final String TEXT_1345 = NL + "\t\t\tcase ";
  protected final String TEXT_1346 = ":";
  protected final String TEXT_1347 = NL + "\t\t\t\t((";
  protected final String TEXT_1348 = ".Internal.Wrapper)";
  protected final String TEXT_1349 = "()).featureMap().clear();";
  protected final String TEXT_1350 = NL + "\t\t\t\t";
  protected final String TEXT_1351 = "().clear();";
  protected final String TEXT_1352 = NL + "\t\t\t\tunset";
  protected final String TEXT_1353 = "();";
  protected final String TEXT_1354 = NL + "\t\t\t\tset";
  protected final String TEXT_1355 = "((";
  protected final String TEXT_1356 = ")null);";
  protected final String TEXT_1357 = NL + "\t\t\t\t";
  protected final String TEXT_1358 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1359 = NL + "\t\t\t\tset";
  protected final String TEXT_1360 = "(Data";
  protected final String TEXT_1361 = ".";
  protected final String TEXT_1362 = ");";
  protected final String TEXT_1363 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1364 = NL + "\t\t}";
  protected final String TEXT_1365 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1366 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1367 = NL + "\t}" + NL;
  protected final String TEXT_1368 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY18" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1369 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1370 = NL + "\t@Override";
  protected final String TEXT_1371 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1372 = ")" + NL + "\t\t{";
  protected final String TEXT_1373 = NL + "\t\t\tcase ";
  protected final String TEXT_1374 = ":";
  protected final String TEXT_1375 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1376 = "();";
  protected final String TEXT_1377 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1378 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1379 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1380 = ".Internal.Wrapper)";
  protected final String TEXT_1381 = "()).featureMap().isEmpty();";
  protected final String TEXT_1382 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1383 = "() != null && !";
  protected final String TEXT_1384 = "().featureMap().isEmpty();";
  protected final String TEXT_1385 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1386 = "() != null && !";
  protected final String TEXT_1387 = "().isEmpty();";
  protected final String TEXT_1388 = NL + "\t\t\t\t";
  protected final String TEXT_1389 = " ";
  protected final String TEXT_1390 = " = (";
  protected final String TEXT_1391 = ")eVirtualGet(";
  protected final String TEXT_1392 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1393 = " != null && !";
  protected final String TEXT_1394 = ".isEmpty();";
  protected final String TEXT_1395 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1396 = "().isEmpty();";
  protected final String TEXT_1397 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1398 = "();";
  protected final String TEXT_1399 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1400 = "() != null;";
  protected final String TEXT_1401 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1402 = ") != null;";
  protected final String TEXT_1403 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1404 = "() != null;";
  protected final String TEXT_1405 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1406 = "() != null;";
  protected final String TEXT_1407 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1408 = ") != null;";
  protected final String TEXT_1409 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1410 = "() != null;";
  protected final String TEXT_1411 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1412 = " & Data";
  protected final String TEXT_1413 = ".";
  protected final String TEXT_1414 = "_EFLAG) != 0) != Data";
  protected final String TEXT_1415 = ".";
  protected final String TEXT_1416 = ";";
  protected final String TEXT_1417 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1418 = " & Data";
  protected final String TEXT_1419 = ".";
  protected final String TEXT_1420 = "_EFLAG) != Data";
  protected final String TEXT_1421 = ".";
  protected final String TEXT_1422 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1423 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1424 = "() != Data";
  protected final String TEXT_1425 = ".";
  protected final String TEXT_1426 = ";";
  protected final String TEXT_1427 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1428 = ", Data";
  protected final String TEXT_1429 = ".";
  protected final String TEXT_1430 = ") != Data";
  protected final String TEXT_1431 = ".";
  protected final String TEXT_1432 = ";";
  protected final String TEXT_1433 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1434 = "() != Data";
  protected final String TEXT_1435 = ".";
  protected final String TEXT_1436 = ";";
  protected final String TEXT_1437 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1438 = ".";
  protected final String TEXT_1439 = " == null ? ";
  protected final String TEXT_1440 = "() != null : !Data";
  protected final String TEXT_1441 = ".";
  protected final String TEXT_1442 = ".equals(";
  protected final String TEXT_1443 = "());";
  protected final String TEXT_1444 = NL + "\t\t\t\t";
  protected final String TEXT_1445 = " ";
  protected final String TEXT_1446 = " = (";
  protected final String TEXT_1447 = ")eVirtualGet(";
  protected final String TEXT_1448 = ", Data";
  protected final String TEXT_1449 = ".";
  protected final String TEXT_1450 = ");" + NL + "\t\t\t\treturn Data";
  protected final String TEXT_1451 = ".";
  protected final String TEXT_1452 = " == null ? ";
  protected final String TEXT_1453 = "() != null : !Data";
  protected final String TEXT_1454 = ".";
  protected final String TEXT_1455 = ".equals(";
  protected final String TEXT_1456 = "());";
  protected final String TEXT_1457 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1458 = ".";
  protected final String TEXT_1459 = " == null ? ";
  protected final String TEXT_1460 = "() != null : !Data";
  protected final String TEXT_1461 = ".";
  protected final String TEXT_1462 = ".equals(";
  protected final String TEXT_1463 = "());";
  protected final String TEXT_1464 = NL + "\t\t}";
  protected final String TEXT_1465 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1466 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1467 = NL + "\t}" + NL;
  protected final String TEXT_1468 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY19" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1469 = NL + "\t@Override";
  protected final String TEXT_1470 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1471 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1472 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1473 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1474 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1475 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1476 = ": return ";
  protected final String TEXT_1477 = ";";
  protected final String TEXT_1478 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1479 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1480 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY20" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1481 = NL + "\t@Override";
  protected final String TEXT_1482 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1483 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1484 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1485 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1486 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1487 = ": return ";
  protected final String TEXT_1488 = ";";
  protected final String TEXT_1489 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1490 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1491 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1492 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1493 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1494 = ": return ";
  protected final String TEXT_1495 = ";";
  protected final String TEXT_1496 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1497 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1498 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY21" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1499 = NL + "\t@Override";
  protected final String TEXT_1500 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1501 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1502 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1503 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1504 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1505 = ": return ";
  protected final String TEXT_1506 = ";";
  protected final String TEXT_1507 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1508 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1509 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1510 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1511 = ": return ";
  protected final String TEXT_1512 = ";";
  protected final String TEXT_1513 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1514 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1515 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1516 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1517 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1518 = ": return ";
  protected final String TEXT_1519 = ";";
  protected final String TEXT_1520 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1521 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1522 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY22" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1523 = NL + "\t@Override";
  protected final String TEXT_1524 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1525 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY23" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1526 = NL + "\t@Override";
  protected final String TEXT_1527 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1528 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1529 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY24" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1530 = NL + "\t@Override";
  protected final String TEXT_1531 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1532 = NL + "\t\t\tcase ";
  protected final String TEXT_1533 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1534 = ";";
  protected final String TEXT_1535 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY25" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1536 = NL + "\t@Override";
  protected final String TEXT_1537 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1538 = NL + "\t\t\tcase ";
  protected final String TEXT_1539 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1540 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1541 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1542 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY26" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1543 = NL + "\t@Override";
  protected final String TEXT_1544 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1545 = "\"unchecked\"";
  protected final String TEXT_1546 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1547 = ")";
  protected final String TEXT_1548 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1549 = " arguments) throws ";
  protected final String TEXT_1550 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1551 = ")" + NL + "\t\t{";
  protected final String TEXT_1552 = NL + "\t\t\tcase ";
  protected final String TEXT_1553 = ":";
  protected final String TEXT_1554 = NL + "\t\t\t\t";
  protected final String TEXT_1555 = "(";
  protected final String TEXT_1556 = "(";
  protected final String TEXT_1557 = "(";
  protected final String TEXT_1558 = ")";
  protected final String TEXT_1559 = "arguments.get(";
  protected final String TEXT_1560 = ")";
  protected final String TEXT_1561 = ").";
  protected final String TEXT_1562 = "()";
  protected final String TEXT_1563 = ", ";
  protected final String TEXT_1564 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1565 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1566 = "new ";
  protected final String TEXT_1567 = "(";
  protected final String TEXT_1568 = "(";
  protected final String TEXT_1569 = "(";
  protected final String TEXT_1570 = "(";
  protected final String TEXT_1571 = ")";
  protected final String TEXT_1572 = "arguments.get(";
  protected final String TEXT_1573 = ")";
  protected final String TEXT_1574 = ").";
  protected final String TEXT_1575 = "()";
  protected final String TEXT_1576 = ", ";
  protected final String TEXT_1577 = ")";
  protected final String TEXT_1578 = ")";
  protected final String TEXT_1579 = ";";
  protected final String TEXT_1580 = NL + "\t\t}";
  protected final String TEXT_1581 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1582 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1583 = NL + "\t}" + NL;
  protected final String TEXT_1584 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY27" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1585 = NL + "\t@Override";
  protected final String TEXT_1586 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());" + NL + "\t\tif (data != null) result.append(data.toString());" + NL + "\t\t" + NL + "\t\treturn result.toString();" + NL + "\t\t}";
  protected final String TEXT_1587 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY28" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1588 = NL + "\t@";
  protected final String TEXT_1589 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY29" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1590 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY30" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY31" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1591 = " getKey()" + NL + "\t{";
  protected final String TEXT_1592 = NL + "\t\treturn new ";
  protected final String TEXT_1593 = "(getTypedKey());";
  protected final String TEXT_1594 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1595 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY32" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1596 = " key)" + NL + "\t{";
  protected final String TEXT_1597 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1598 = "(";
  protected final String TEXT_1599 = ")";
  protected final String TEXT_1600 = "key);";
  protected final String TEXT_1601 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1602 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1603 = ")key).";
  protected final String TEXT_1604 = "());";
  protected final String TEXT_1605 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1606 = ")key);";
  protected final String TEXT_1607 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY33" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1608 = " getValue()" + NL + "\t{";
  protected final String TEXT_1609 = NL + "\t\treturn new ";
  protected final String TEXT_1610 = "(getTypedValue());";
  protected final String TEXT_1611 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1612 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY34" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1613 = " setValue(";
  protected final String TEXT_1614 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1615 = " oldValue = getValue();";
  protected final String TEXT_1616 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1617 = "(";
  protected final String TEXT_1618 = ")";
  protected final String TEXT_1619 = "value);";
  protected final String TEXT_1620 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1621 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1622 = ")value).";
  protected final String TEXT_1623 = "());";
  protected final String TEXT_1624 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1625 = ")value);";
  protected final String TEXT_1626 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY35" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1627 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1628 = NL + "\tpublic ";
  protected final String TEXT_1629 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1630 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1631 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1632 = NL + NL + NL + NL;
  protected final String TEXT_1633 = NL + "// data Class generation " + NL + "protected static  class Data";
  protected final String TEXT_1634 = NL + NL + "{" + NL;
  protected final String TEXT_1635 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_1636 = " copyright = ";
  protected final String TEXT_1637 = ";";
  protected final String TEXT_1638 = NL;
  protected final String TEXT_1639 = NL;
  protected final String TEXT_1640 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_1641 = "() <em>";
  protected final String TEXT_1642 = "</em>}' ";
  protected final String TEXT_1643 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1644 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1645 = NL + "\t@";
  protected final String TEXT_1646 = NL + "\tprotected ";
  protected final String TEXT_1647 = ".Internal.SettingDelegate ";
  protected final String TEXT_1648 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_1649 = ".Internal)";
  protected final String TEXT_1650 = ").getSettingDelegate();" + NL;
  protected final String TEXT_1651 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1652 = "() <em>";
  protected final String TEXT_1653 = "</em>}' ";
  protected final String TEXT_1654 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1655 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1656 = NL + "\t@";
  protected final String TEXT_1657 = NL + "\tprotected ";
  protected final String TEXT_1658 = " ";
  protected final String TEXT_1659 = ";" + NL;
  protected final String TEXT_1660 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_1661 = "() <em>";
  protected final String TEXT_1662 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1663 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1664 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1665 = NL + "\tprotected static final ";
  protected final String TEXT_1666 = "[] ";
  protected final String TEXT_1667 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_1668 = " [0]";
  protected final String TEXT_1669 = ";" + NL;
  protected final String TEXT_1670 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_1671 = "() <em>";
  protected final String TEXT_1672 = "</em>}' ";
  protected final String TEXT_1673 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1674 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1675 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1676 = NL + "\tprotected static final ";
  protected final String TEXT_1677 = " ";
  protected final String TEXT_1678 = "; // TODO The default value literal \"";
  protected final String TEXT_1679 = "\" is not valid.";
  protected final String TEXT_1680 = " = ";
  protected final String TEXT_1681 = ";";
  protected final String TEXT_1682 = NL;
  protected final String TEXT_1683 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1684 = NL + "\t@";
  protected final String TEXT_1685 = NL + "\tprotected int ";
  protected final String TEXT_1686 = " = 0;" + NL;
  protected final String TEXT_1687 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_1688 = "() <em>";
  protected final String TEXT_1689 = "</em>}' ";
  protected final String TEXT_1690 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1691 = "_EFLAG_OFFSET = ";
  protected final String TEXT_1692 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_1693 = "() <em>";
  protected final String TEXT_1694 = "</em>}' ";
  protected final String TEXT_1695 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1696 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_1697 = ".ordinal()";
  protected final String TEXT_1698 = ".VALUES.indexOf(";
  protected final String TEXT_1699 = ")";
  protected final String TEXT_1700 = " << ";
  protected final String TEXT_1701 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_1702 = " ";
  protected final String TEXT_1703 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_1704 = "[] ";
  protected final String TEXT_1705 = "_EFLAG_VALUES = ";
  protected final String TEXT_1706 = ".values()";
  protected final String TEXT_1707 = "(";
  protected final String TEXT_1708 = "[])";
  protected final String TEXT_1709 = ".VALUES.toArray(new ";
  protected final String TEXT_1710 = "[";
  protected final String TEXT_1711 = ".VALUES.size()])";
  protected final String TEXT_1712 = ";" + NL;
  protected final String TEXT_1713 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_1714 = " representing the value of the '{@link #";
  protected final String TEXT_1715 = "() <em>";
  protected final String TEXT_1716 = "</em>}' ";
  protected final String TEXT_1717 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1718 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1719 = "_EFLAG = ";
  protected final String TEXT_1720 = " << ";
  protected final String TEXT_1721 = "_EFLAG_OFFSET";
  protected final String TEXT_1722 = ";" + NL;
  protected final String TEXT_1723 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1724 = "() <em>";
  protected final String TEXT_1725 = "</em>}' ";
  protected final String TEXT_1726 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1727 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1728 = NL + "\t@";
  protected final String TEXT_1729 = NL + "\tprotected ";
  protected final String TEXT_1730 = " ";
  protected final String TEXT_1731 = " = ";
  protected final String TEXT_1732 = ";" + NL;
  protected final String TEXT_1733 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1734 = NL + "\t@";
  protected final String TEXT_1735 = NL + "\tprotected int ";
  protected final String TEXT_1736 = " = 0;" + NL;
  protected final String TEXT_1737 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_1738 = " ";
  protected final String TEXT_1739 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1740 = "_ESETFLAG = 1 << ";
  protected final String TEXT_1741 = ";" + NL;
  protected final String TEXT_1742 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_1743 = " ";
  protected final String TEXT_1744 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1745 = NL + "\t@";
  protected final String TEXT_1746 = NL + "\tprotected boolean ";
  protected final String TEXT_1747 = "ESet;" + NL;
  protected final String TEXT_1748 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_1749 = ".getOperationID(";
  protected final String TEXT_1750 = ") - ";
  protected final String TEXT_1751 = ";" + NL;
  protected final String TEXT_1752 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1753 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Data";
  protected final String TEXT_1754 = "()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1755 = " super(); ";
  protected final String TEXT_1756 = NL + "\t\t";
  protected final String TEXT_1757 = " |= ";
  protected final String TEXT_1758 = "_EFLAG";
  protected final String TEXT_1759 = "_DEFAULT";
  protected final String TEXT_1760 = ";";
  protected final String TEXT_1761 = NL + "\t}" + NL + "\t" + NL + "\t\t";
  protected final String TEXT_1762 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1763 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param {@link ";
  protected final String TEXT_1764 = " }" + NL + "\t * @generated" + NL + "\t */" + NL + "\t//public Data";
  protected final String TEXT_1765 = "(Data";
  protected final String TEXT_1766 = " data)" + NL + "\t//{" + NL + "\t//\tthis();\t\t" + NL + "\t//\t";
  protected final String TEXT_1767 = NL + "\t//\t";
  protected final String TEXT_1768 = " = data.";
  protected final String TEXT_1769 = ";";
  protected final String TEXT_1770 = NL + "\t//\t}" + NL + "\t";
  protected final String TEXT_1771 = NL + "\t";
  protected final String TEXT_1772 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString(){\t" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1773 = "\t\t";
  protected final String TEXT_1774 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1775 = ": \");";
  protected final String TEXT_1776 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1777 = ": \");";
  protected final String TEXT_1778 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1779 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1780 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1781 = NL + "\t\tif (";
  protected final String TEXT_1782 = "(";
  protected final String TEXT_1783 = " & ";
  protected final String TEXT_1784 = "_ESETFLAG) != 0";
  protected final String TEXT_1785 = "ESet";
  protected final String TEXT_1786 = ") result.append((";
  protected final String TEXT_1787 = " & ";
  protected final String TEXT_1788 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1789 = NL + "\t\tif (";
  protected final String TEXT_1790 = "(";
  protected final String TEXT_1791 = " & ";
  protected final String TEXT_1792 = "_ESETFLAG) != 0";
  protected final String TEXT_1793 = "ESet";
  protected final String TEXT_1794 = ") result.append(";
  protected final String TEXT_1795 = "_EFLAG_VALUES[(";
  protected final String TEXT_1796 = " & ";
  protected final String TEXT_1797 = "_EFLAG) >>> ";
  protected final String TEXT_1798 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1799 = NL + "\t\tif (";
  protected final String TEXT_1800 = "(";
  protected final String TEXT_1801 = " & ";
  protected final String TEXT_1802 = "_ESETFLAG) != 0";
  protected final String TEXT_1803 = "ESet";
  protected final String TEXT_1804 = ") result.append(";
  protected final String TEXT_1805 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1806 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1807 = ", ";
  protected final String TEXT_1808 = "));";
  protected final String TEXT_1809 = NL + "\t\tresult.append((";
  protected final String TEXT_1810 = " & ";
  protected final String TEXT_1811 = "_EFLAG) != 0);";
  protected final String TEXT_1812 = NL + "\t\tresult.append(";
  protected final String TEXT_1813 = "_EFLAG_VALUES[(";
  protected final String TEXT_1814 = " & ";
  protected final String TEXT_1815 = "_EFLAG) >>> ";
  protected final String TEXT_1816 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1817 = NL + "\t\tresult.append(";
  protected final String TEXT_1818 = ");";
  protected final String TEXT_1819 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL + "\t";
  protected final String TEXT_1820 = "\t";
  protected final String TEXT_1821 = NL + "}//end data class";
  protected final String TEXT_1822 = NL + "} //";
  protected final String TEXT_1823 = NL;

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
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfNotification"));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_314);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfNotification"));
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_320);
    } else {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfNotification"));
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_330);
    }
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfNotification"));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_333);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_334);
    }
    }
    } 	
	else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_344);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_348);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_351);
    } else {
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_353);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_356);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_358);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_360);
    } else {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_363);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_364);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_365);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_367);
    }
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_369);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_371);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_373);
    }
    stringBuffer.append(TEXT_374);
    } else {
    stringBuffer.append(TEXT_375);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_376);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_378);
    }
    stringBuffer.append(TEXT_379);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_381);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_383);
    }
    stringBuffer.append(TEXT_384);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_389);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_390);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_391);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_392);
    } else {
    stringBuffer.append(TEXT_393);
    }
    stringBuffer.append(TEXT_394);
    }
    stringBuffer.append(TEXT_395);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_396);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_397);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_400);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_404);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_405);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_406);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_407);
    }
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_410);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_412);
    }
    stringBuffer.append(TEXT_413);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_416);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_419);
    } else {
    stringBuffer.append(TEXT_420);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_421);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_424);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_426);
    } else {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_430);
    }
    } else {
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_433);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_434);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_435);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_441);
     if (! genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && ! genFeature.hasSettingDelegate() && ! genFeature.isContainer() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_442);
    }
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_446);
    stringBuffer.append(TEXT_447);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_451);
    stringBuffer.append(TEXT_452);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_456);
    } else {
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_459);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_462);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_464);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_468);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_471);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_474);
    }
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_476);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_477);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_482);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_486);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_487);
    } else {
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_489);
    }
    stringBuffer.append(TEXT_490);
    } else {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_495);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_500);
    }
    stringBuffer.append(TEXT_501);
    }
    stringBuffer.append(TEXT_502);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_505);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_508);
    } else {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_513);
    }
    } else {
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_516);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_517);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_525);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_528);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_530);
    }
    }
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_532);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_533);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_536);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_538);
    }
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_540);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(TEXT_542);
     if (!genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && !genFeature.hasSettingDelegate() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_543);
    if (CodegenUtil.getDataClassExtends(genClass) != ""){
    stringBuffer.append(TEXT_544);
    }}
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_547);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_549);
    }
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_551);
    }
    stringBuffer.append(TEXT_552);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_554);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_556);
    }
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_558);
    }
    stringBuffer.append(TEXT_559);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_561);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_563);
    }
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_565);
    }
    stringBuffer.append(TEXT_566);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_580);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_584);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_590);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_595);
    }
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_597);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_599);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_600);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_602);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_608);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_610);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_612);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_617);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_618);
    }
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_622);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_623);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_626);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_630);
    }
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_633);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_636);
    }
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_638);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_645);
    }
    stringBuffer.append(TEXT_646);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_652);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_658);
    } else {
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_666);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_674);
    } else {
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_682);
    if (isJDK50) {
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_684);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_686);
    }
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_688);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_691);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_692);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_699);
    } else {
    stringBuffer.append(TEXT_700);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_702);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_704);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_709);
    } else {
    stringBuffer.append(TEXT_710);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_713);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_717);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_719);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_723);
    }
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_726);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_729);
    }
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_731);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_735);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_740);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_742);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_743);
    } else {
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_745);
    }
    stringBuffer.append(TEXT_746);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_750);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_752);
    stringBuffer.append( genFeature.getEDefault().equals("null") ? "" : "Data"+genClass.getInterfaceName()+".");
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getCapName());
    }else {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_755);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_757);
    }
    }
    }
    } 
	else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_760);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_762);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_764);
    }
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_766);
    }
    stringBuffer.append(TEXT_767);
    } else {
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_769);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_771);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_773);
    }
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_775);
    }
    stringBuffer.append(TEXT_776);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_777);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_780);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_781);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_782);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_786);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_788);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_791);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_793);
    }
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_795);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_799);
    }
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_801);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_803);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_807);
    }
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_810);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_813);
    }
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_815);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_820);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_822);
    } else {
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_824);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_825);
    } else {
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_827);
    }
    stringBuffer.append(TEXT_828);
    }
    } else {
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_831);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_832);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_837);
    stringBuffer.append(TEXT_838);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_840);
    }
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_842);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_845);
    }
    stringBuffer.append(TEXT_846);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_847);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_849);
    } else {
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_851);
    }
    stringBuffer.append(TEXT_852);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_855);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_857);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_859);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_864);
    }
    stringBuffer.append(TEXT_865);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_867);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_869);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_874);
    }
    stringBuffer.append(TEXT_875);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_877);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_879);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_881);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_883);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_884);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_885);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_886);
    }
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_888);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_891);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_895);
    }
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_898);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_901);
    }
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_903);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_908);
    }
    stringBuffer.append(TEXT_909);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_914);
    } else {
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_921);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_924);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_925);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_928);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_930);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_934);
    } else {
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_937);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_939);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_942);
    } else {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_944);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_948);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_950);
    } else {
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_952);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_953);
    } else {
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_955);
    }
    stringBuffer.append(TEXT_956);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_962);
    } else {
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_967);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_970);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_973);
    } else {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_975);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_979);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_986);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_987);
    } else {
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_989);
    }
    stringBuffer.append(TEXT_990);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_993);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_995);
    } else {
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_997);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_999);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1003);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1004);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1012);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1014);
    }
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1016);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1019);
    }
    stringBuffer.append(TEXT_1020);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1021);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1023);
    } else {
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1025);
    }
    stringBuffer.append(TEXT_1026);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1029);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1031);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1033);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1038);
    }
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1043);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1045);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1048);
    } else {
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1050);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1055);
    } else {
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1059);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1063);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1064);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1074);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1084);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(TEXT_1086);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1087);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1093);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1096);
    }}
    stringBuffer.append(TEXT_1097);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1098);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1103);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1104);
    }
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1109);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1123);
    } else {
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1136);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1137);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1139);
    if (size > 0) {
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1143);
    } else {
    stringBuffer.append(TEXT_1144);
    }
    stringBuffer.append(TEXT_1145);
    } else {
    stringBuffer.append(TEXT_1146);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1147);
    }
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1150);
    if (size > 0) {
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1154);
    } else {
    stringBuffer.append(TEXT_1155);
    }
    stringBuffer.append(TEXT_1156);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1158);
    }
    stringBuffer.append(TEXT_1159);
    }
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1162);
    } else {
    stringBuffer.append(TEXT_1163);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1164);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1165);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1166);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1167);
    }
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1172);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1174);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1179);
    } else {
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1181);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1182);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1185);
    } else {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1187);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1192);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1195);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1197);
    }
    stringBuffer.append(TEXT_1198);
    stringBuffer.append( (genModel.isVirtualDelegation()|| genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1199);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1201);
    stringBuffer.append( (genModel.isVirtualDelegation() || genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1203);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1205);
    stringBuffer.append( (genModel.isVirtualDelegation()|| genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1208);
    }
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1211);
    }
    }
    stringBuffer.append(TEXT_1212);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1213);
    } else {
    stringBuffer.append(TEXT_1214);
    }
    stringBuffer.append(TEXT_1215);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1216);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1217);
    }
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1222);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1224);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1229);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1233);
    } else {
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1236);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1238);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1240);
    } else {
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1242);
    }
    }
    stringBuffer.append(TEXT_1243);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1244);
    } else {
    stringBuffer.append(TEXT_1245);
    }
    stringBuffer.append(TEXT_1246);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1247);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1248);
    }
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1252);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1254);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1256);
    }
    stringBuffer.append(TEXT_1257);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1258);
    } else {
    stringBuffer.append(TEXT_1259);
    }
    stringBuffer.append(TEXT_1260);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1261);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1262);
    }
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1264);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1266);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1268);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1270);
    } else {
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1273);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1276);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1281);
    } else {
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1284);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1288);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1292);
    } else {
    stringBuffer.append(TEXT_1293);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1294);
    }
    }
    stringBuffer.append(TEXT_1295);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1296);
    } else {
    stringBuffer.append(TEXT_1297);
    }
    stringBuffer.append(TEXT_1298);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1299);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1300);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1301);
    }
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1303);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1305);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1309);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1312);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1316);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1317);
    } else {
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1320);
    }
    } else {
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1323);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1325);
    }
    stringBuffer.append(TEXT_1326);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1330);
    } else {
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1332);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1334);
    }
    stringBuffer.append(TEXT_1335);
    }
    stringBuffer.append(TEXT_1336);
    }
    stringBuffer.append(TEXT_1337);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1338);
    } else {
    stringBuffer.append(TEXT_1339);
    }
    stringBuffer.append(TEXT_1340);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1341);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1342);
    }
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1344);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1346);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1347);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1349);
    } else {
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1351);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1353);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1355);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1356);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1358);
    } else {
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1360);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1361);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1362);
    }
    stringBuffer.append(TEXT_1363);
    }
    stringBuffer.append(TEXT_1364);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1365);
    } else {
    stringBuffer.append(TEXT_1366);
    }
    stringBuffer.append(TEXT_1367);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1368);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1369);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1370);
    }
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1372);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1374);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1376);
    } else {
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1378);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1381);
    } else {
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1383);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1384);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1387);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1388);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1392);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1393);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1394);
    } else {
    stringBuffer.append(TEXT_1395);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1396);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1397);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1398);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1400);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1402);
    } else {
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1404);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1406);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1408);
    } else {
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1410);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1416);
    } else {
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1418);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1422);
    }
    } else {
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1424);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1425);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1426);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1430);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1431);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1432);
    } else {
    stringBuffer.append(TEXT_1433);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1436);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1437);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1438);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1439);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1440);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1441);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1442);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1443);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1444);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1448);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1454);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1456);
    } else {
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1458);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1460);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1461);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1462);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1463);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1464);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1465);
    } else {
    stringBuffer.append(TEXT_1466);
    }
    stringBuffer.append(TEXT_1467);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1468);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1469);
    }
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1471);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1472);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1474);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1475);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1476);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1477);
    }
    stringBuffer.append(TEXT_1478);
    }
    stringBuffer.append(TEXT_1479);
    }
    stringBuffer.append(TEXT_1480);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1481);
    }
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1483);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1485);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1488);
    }
    stringBuffer.append(TEXT_1489);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1490);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1491);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1492);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1494);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1495);
    }
    stringBuffer.append(TEXT_1496);
    }
    stringBuffer.append(TEXT_1497);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1498);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1499);
    }
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1501);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1503);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1505);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1506);
    }
    }
    stringBuffer.append(TEXT_1507);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1508);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1509);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1510);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1512);
    }
    stringBuffer.append(TEXT_1513);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1514);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1516);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1517);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1518);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1519);
    }
    stringBuffer.append(TEXT_1520);
    }
    stringBuffer.append(TEXT_1521);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1522);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1523);
    }
    stringBuffer.append(TEXT_1524);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1525);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1526);
    }
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1528);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1529);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1530);
    }
    stringBuffer.append(TEXT_1531);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1533);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1534);
    }
    stringBuffer.append(TEXT_1535);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1536);
    }
    stringBuffer.append(TEXT_1537);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1538);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1539);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1540);
    }
    stringBuffer.append(TEXT_1541);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1542);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1543);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1544);
    if (!isRaw) {
    stringBuffer.append(TEXT_1545);
    } else {
    stringBuffer.append(TEXT_1546);
    }
    stringBuffer.append(TEXT_1547);
    }
    }
    stringBuffer.append(TEXT_1548);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1549);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1550);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1551);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1552);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1553);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1554);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1555);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1556);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1557);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1558);
    }
    stringBuffer.append(TEXT_1559);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1560);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1562);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1563);
    }
    }
    stringBuffer.append(TEXT_1564);
    } else {
    stringBuffer.append(TEXT_1565);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1566);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1567);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1568);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1569);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1570);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1571);
    }
    stringBuffer.append(TEXT_1572);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1573);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1574);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1575);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1576);
    }
    }
    stringBuffer.append(TEXT_1577);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1578);
    }
    stringBuffer.append(TEXT_1579);
    }
    }
    stringBuffer.append(TEXT_1580);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1581);
    } else {
    stringBuffer.append(TEXT_1582);
    }
    stringBuffer.append(TEXT_1583);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1584);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1585);
    }
    stringBuffer.append(TEXT_1586);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1587);
    if (isGWT) {
    stringBuffer.append(TEXT_1588);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1589);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1590);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1591);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1592);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1593);
    } else {
    stringBuffer.append(TEXT_1594);
    }
    stringBuffer.append(TEXT_1595);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1596);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1597);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1599);
    }
    stringBuffer.append(TEXT_1600);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1601);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1602);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1603);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1604);
    } else {
    stringBuffer.append(TEXT_1605);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1606);
    }
    stringBuffer.append(TEXT_1607);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1608);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1609);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1610);
    } else {
    stringBuffer.append(TEXT_1611);
    }
    stringBuffer.append(TEXT_1612);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1614);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1615);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1616);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1617);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1618);
    }
    stringBuffer.append(TEXT_1619);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1620);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1621);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1622);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1623);
    } else {
    stringBuffer.append(TEXT_1624);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1625);
    }
    stringBuffer.append(TEXT_1626);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1627);
    }
    stringBuffer.append(TEXT_1628);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1629);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1630);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1631);
    }
    stringBuffer.append(TEXT_1632);
    if (isImplementation && (!(genModel.isReflectiveDelegation()))) {
    stringBuffer.append(TEXT_1633);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(CodegenUtil.getDataClassExtends(genClass));
    stringBuffer.append(TEXT_1634);
     if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_1635);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1636);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_1637);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1638);
    }
    stringBuffer.append(TEXT_1639);
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1640);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1641);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1642);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1643);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1644);
    if (isGWT) {
    stringBuffer.append(TEXT_1645);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1646);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1647);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1648);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1649);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1650);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1651);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1652);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1653);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1654);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1655);
    if (isGWT) {
    stringBuffer.append(TEXT_1656);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1657);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_1658);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1659);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_1660);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1661);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1662);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1663);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_1664);
    }
    stringBuffer.append(TEXT_1665);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1667);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_1669);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_1670);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1671);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1673);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1674);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_1675);
    }
    stringBuffer.append(TEXT_1676);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1677);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_1678);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_1679);
    } else {
    stringBuffer.append(TEXT_1680);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_1682);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1683);
    if (isGWT) {
    stringBuffer.append(TEXT_1684);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1685);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1686);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_1687);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1689);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1690);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1691);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_1692);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1693);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1694);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1695);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1696);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1697);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1699);
    }
    stringBuffer.append(TEXT_1700);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1701);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1702);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_1703);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1704);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1705);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1706);
    } else {
    stringBuffer.append(TEXT_1707);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1708);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1709);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1710);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1711);
    }
    stringBuffer.append(TEXT_1712);
    }
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_1714);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1715);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1716);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1717);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1718);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_1720);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1721);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_1722);
    } else {
    stringBuffer.append(TEXT_1723);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1725);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1726);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1727);
    if (isGWT) {
    stringBuffer.append(TEXT_1728);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1730);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1731);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1732);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1733);
    if (isGWT) {
    stringBuffer.append(TEXT_1734);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1735);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1736);
    }
    stringBuffer.append(TEXT_1737);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1739);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1740);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_1741);
    } else {
    stringBuffer.append(TEXT_1742);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1743);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1744);
    if (isGWT) {
    stringBuffer.append(TEXT_1745);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1746);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1747);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1749);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1750);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_1751);
    }
    if (isImplementation ) {
    stringBuffer.append(TEXT_1752);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1753);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1754);
    if ( CodegenUtil.getDataClassExtends(genClass) !=""){
    stringBuffer.append(TEXT_1755);
    }
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_1756);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1757);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1758);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1759);
    }
    stringBuffer.append(TEXT_1760);
    }
    stringBuffer.append(TEXT_1761);
    if ( CodegenUtil.getDataClassExtends(genClass) !=""){
    stringBuffer.append(TEXT_1762);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1763);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
    stringBuffer.append(TEXT_1764);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1765);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
    stringBuffer.append(TEXT_1766);
     for (GenFeature genFeat : genClass.getClassExtendsGenClass().getDeclaredFieldGenFeatures()){
    stringBuffer.append(TEXT_1767);
    stringBuffer.append(genFeat.getSafeName());
    stringBuffer.append(TEXT_1768);
    stringBuffer.append(genFeat.getSafeName());
    stringBuffer.append(TEXT_1769);
    }
    stringBuffer.append(TEXT_1770);
    }
    stringBuffer.append(TEXT_1771);
    if (!genModel.isDynamicDelegation() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_1772);
    { boolean first = true;
    stringBuffer.append(TEXT_1773);
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1774);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1775);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1776);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1777);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1778);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1779);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1780);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1781);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1782);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1783);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1784);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1785);
    }
    stringBuffer.append(TEXT_1786);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1787);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1788);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1789);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1790);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1791);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1792);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1793);
    }
    stringBuffer.append(TEXT_1794);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1795);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1796);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1797);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1798);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1799);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1800);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1801);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1802);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1803);
    }
    stringBuffer.append(TEXT_1804);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1805);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1806);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1807);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1808);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1809);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1810);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1811);
    } else {
    stringBuffer.append(TEXT_1812);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1813);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1814);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1815);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1816);
    }
    } else {
    stringBuffer.append(TEXT_1817);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1818);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1819);
    }
    stringBuffer.append(TEXT_1820);
    }
    stringBuffer.append(TEXT_1821);
    } 
    stringBuffer.append(TEXT_1822);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1823);
    return stringBuffer.toString();
  }
}
