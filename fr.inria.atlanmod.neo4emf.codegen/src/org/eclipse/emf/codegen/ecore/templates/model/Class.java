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
  protected final String TEXT_48 = NL;
  protected final String TEXT_49 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_50 = " mofDriverNumber = \"";
  protected final String TEXT_51 = "\";";
  protected final String TEXT_52 = NL;
  protected final String TEXT_53 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_54 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_55 = NL + "\t@";
  protected final String TEXT_56 = NL + "\tprotected Object[] ";
  protected final String TEXT_57 = ";" + NL;
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_59 = NL + "\t@";
  protected final String TEXT_60 = NL + "\tprotected int ";
  protected final String TEXT_61 = ";" + NL;
  protected final String TEXT_62 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_63 = NL + "\t@";
  protected final String TEXT_64 = NL + "\tprotected int ";
  protected final String TEXT_65 = " = 0;" + NL;
  protected final String TEXT_66 = NL;
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_68 = " = ";
  protected final String TEXT_69 = ".getFeatureID(";
  protected final String TEXT_70 = ") - ";
  protected final String TEXT_71 = ";" + NL;
  protected final String TEXT_72 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_73 = " = ";
  protected final String TEXT_74 = ".getFeatureID(";
  protected final String TEXT_75 = ") - ";
  protected final String TEXT_76 = ";" + NL;
  protected final String TEXT_77 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_78 = ".getOperationID(";
  protected final String TEXT_79 = ") - ";
  protected final String TEXT_80 = ";" + NL;
  protected final String TEXT_81 = NL + "\t " + NL + "\t//";
  protected final String TEXT_82 = "\t " + NL + "\t//";
  protected final String TEXT_83 = NL + "\t";
  protected final String TEXT_84 = NL + "\t/**" + NL + "\t * The cached value of the data structure {@link Data";
  protected final String TEXT_85 = " <em>data</em> } " + NL + "\t * @generated" + NL + "\t */" + NL + "\t \tprotected Data";
  protected final String TEXT_86 = " data;" + NL + "\t ";
  protected final String TEXT_87 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_88 = "public";
  protected final String TEXT_89 = "protected";
  protected final String TEXT_90 = " ";
  protected final String TEXT_91 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t\t";
  protected final String TEXT_92 = NL + "\t\t";
  protected final String TEXT_93 = " |= ";
  protected final String TEXT_94 = "_EFLAG";
  protected final String TEXT_95 = "_DEFAULT";
  protected final String TEXT_96 = ";";
  protected final String TEXT_97 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_98 = NL + "\t@Override";
  protected final String TEXT_99 = NL + "\tprotected ";
  protected final String TEXT_100 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_101 = ";" + NL + "\t}" + NL;
  protected final String TEXT_102 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_103 = NL + "\t@Override";
  protected final String TEXT_104 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_105 = ";" + NL + "\t}" + NL;
  protected final String TEXT_106 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX1" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_107 = NL + "\t";
  protected final String TEXT_108 = "[] ";
  protected final String TEXT_109 = "();" + NL;
  protected final String TEXT_110 = NL + "\tpublic ";
  protected final String TEXT_111 = "[] ";
  protected final String TEXT_112 = "()" + NL + "\t{";
  protected final String TEXT_113 = NL + "\t\t";
  protected final String TEXT_114 = " list = (";
  protected final String TEXT_115 = ")";
  protected final String TEXT_116 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_117 = "(";
  protected final String TEXT_118 = "[])";
  protected final String TEXT_119 = "_EEMPTY_ARRAY;";
  protected final String TEXT_120 = NL + "\t\tif (";
  protected final String TEXT_121 = " == null || ";
  protected final String TEXT_122 = ".isEmpty()) return ";
  protected final String TEXT_123 = "(";
  protected final String TEXT_124 = "[])";
  protected final String TEXT_125 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_126 = " list = (";
  protected final String TEXT_127 = ")";
  protected final String TEXT_128 = ";";
  protected final String TEXT_129 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_130 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_131 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX2" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_132 = NL + "\t";
  protected final String TEXT_133 = " get";
  protected final String TEXT_134 = "(int index);" + NL;
  protected final String TEXT_135 = NL + "\tpublic ";
  protected final String TEXT_136 = " get";
  protected final String TEXT_137 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_138 = "(";
  protected final String TEXT_139 = ")";
  protected final String TEXT_140 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_141 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX3" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_142 = NL + "\tint get";
  protected final String TEXT_143 = "Length();" + NL;
  protected final String TEXT_144 = NL + "\tpublic int get";
  protected final String TEXT_145 = "Length()" + NL + "\t{";
  protected final String TEXT_146 = NL + "\t\treturn ";
  protected final String TEXT_147 = "().size();";
  protected final String TEXT_148 = NL + "\t\treturn ";
  protected final String TEXT_149 = " == null ? 0 : ";
  protected final String TEXT_150 = ".size();";
  protected final String TEXT_151 = NL + "\t}" + NL;
  protected final String TEXT_152 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX4" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_153 = NL + "\tvoid set";
  protected final String TEXT_154 = "(";
  protected final String TEXT_155 = "[] new";
  protected final String TEXT_156 = ");" + NL;
  protected final String TEXT_157 = NL + "\tpublic void set";
  protected final String TEXT_158 = "(";
  protected final String TEXT_159 = "[] new";
  protected final String TEXT_160 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_161 = ")";
  protected final String TEXT_162 = "()).setData(new";
  protected final String TEXT_163 = ".length, new";
  protected final String TEXT_164 = ");" + NL + "\t}" + NL;
  protected final String TEXT_165 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX5" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_166 = NL + "\tvoid set";
  protected final String TEXT_167 = "(int index, ";
  protected final String TEXT_168 = " element);" + NL;
  protected final String TEXT_169 = NL + "\tpublic void set";
  protected final String TEXT_170 = "(int index, ";
  protected final String TEXT_171 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_172 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_173 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_174 = "</b></em>' ";
  protected final String TEXT_175 = ".";
  protected final String TEXT_176 = NL + "\t * The key is of type ";
  protected final String TEXT_177 = "list of {@link ";
  protected final String TEXT_178 = "}";
  protected final String TEXT_179 = "{@link ";
  protected final String TEXT_180 = "}";
  protected final String TEXT_181 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_182 = "list of {@link ";
  protected final String TEXT_183 = "}";
  protected final String TEXT_184 = "{@link ";
  protected final String TEXT_185 = "}";
  protected final String TEXT_186 = ",";
  protected final String TEXT_187 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_188 = "}";
  protected final String TEXT_189 = ".";
  protected final String TEXT_190 = NL + "\t * The default value is <code>";
  protected final String TEXT_191 = "</code>.";
  protected final String TEXT_192 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_193 = "}.";
  protected final String TEXT_194 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_195 = "#";
  protected final String TEXT_196 = " <em>";
  protected final String TEXT_197 = "</em>}'.";
  protected final String TEXT_198 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX6a";
  protected final String TEXT_199 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_200 = "</em>' ";
  protected final String TEXT_201 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_202 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_203 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t *XX6b" + NL + "\t * ";
  protected final String TEXT_204 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_205 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_206 = "</em>' ";
  protected final String TEXT_207 = ".";
  protected final String TEXT_208 = NL + "\t * @see ";
  protected final String TEXT_209 = NL + "\t * @see #isSet";
  protected final String TEXT_210 = "()";
  protected final String TEXT_211 = NL + "\t * @see #unset";
  protected final String TEXT_212 = "()";
  protected final String TEXT_213 = NL + "\t * @see #set";
  protected final String TEXT_214 = "(";
  protected final String TEXT_215 = ")";
  protected final String TEXT_216 = NL + "\t * @see ";
  protected final String TEXT_217 = "#get";
  protected final String TEXT_218 = "()";
  protected final String TEXT_219 = NL + "\t * @see ";
  protected final String TEXT_220 = "#";
  protected final String TEXT_221 = NL + "\t * @model ";
  protected final String TEXT_222 = NL + "\t *        ";
  protected final String TEXT_223 = NL + "\t * @model";
  protected final String TEXT_224 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_225 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX7" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_226 = NL + "\t";
  protected final String TEXT_227 = " ";
  protected final String TEXT_228 = "();";
  protected final String TEXT_229 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_230 = NL + "\tpublic ";
  protected final String TEXT_231 = " ";
  protected final String TEXT_232 = "_";
  protected final String TEXT_233 = "()" + NL + "\t{";
  protected final String TEXT_234 = NL + "\t\treturn ";
  protected final String TEXT_235 = "(";
  protected final String TEXT_236 = "(";
  protected final String TEXT_237 = ")eDynamicGet(";
  protected final String TEXT_238 = ", ";
  protected final String TEXT_239 = ", true, ";
  protected final String TEXT_240 = ")";
  protected final String TEXT_241 = ").";
  protected final String TEXT_242 = "()";
  protected final String TEXT_243 = ";";
  protected final String TEXT_244 = NL + "\t\treturn ";
  protected final String TEXT_245 = "(";
  protected final String TEXT_246 = "(";
  protected final String TEXT_247 = ")eGet(";
  protected final String TEXT_248 = ", true)";
  protected final String TEXT_249 = ").";
  protected final String TEXT_250 = "()";
  protected final String TEXT_251 = ";";
  protected final String TEXT_252 = NL + "\t\treturn ";
  protected final String TEXT_253 = "(";
  protected final String TEXT_254 = "(";
  protected final String TEXT_255 = ")";
  protected final String TEXT_256 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_257 = ").";
  protected final String TEXT_258 = "()";
  protected final String TEXT_259 = ";";
  protected final String TEXT_260 = "      " + NL + "\t\t";
  protected final String TEXT_261 = " ";
  protected final String TEXT_262 = " = (";
  protected final String TEXT_263 = ")eVirtualGet(";
  protected final String TEXT_264 = ");" + NL + "\t  ";
  protected final String TEXT_265 = NL + "\t   ";
  protected final String TEXT_266 = NL + "\t\tif (getNodeId()<0 && data == null ) " + NL + "\t\t\treturn null;" + NL + "\t\tif ( data == null || !(data instanceof Data";
  protected final String TEXT_267 = "))" + NL + "\t\t\tdata = new Data";
  protected final String TEXT_268 = "();" + NL + "\t\t";
  protected final String TEXT_269 = "\t" + NL + "\t\tif (((Data";
  protected final String TEXT_270 = ")data).";
  protected final String TEXT_271 = " == null)\t((INeo4emfResource) this.eResource()).fetchAttributes(this);" + NL + "\t\t";
  protected final String TEXT_272 = "\t\t" + NL + "\t  ";
  protected final String TEXT_273 = " " + NL + "\t\tif (((Data";
  protected final String TEXT_274 = ")data).";
  protected final String TEXT_275 = " == null)\t" + NL + "\t\t{";
  protected final String TEXT_276 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_277 = ", ";
  protected final String TEXT_278 = " = new ";
  protected final String TEXT_279 = ");";
  protected final String TEXT_280 = NL + "\t\t\t((Data";
  protected final String TEXT_281 = ")data).";
  protected final String TEXT_282 = " = new ";
  protected final String TEXT_283 = ";" + NL + "\t\t\tif (getNodeId()>-1) " + NL + "\t\t\t((INeo4emfResource) this.eResource()).getOnDemand(this, ";
  protected final String TEXT_284 = ");\t\t\t";
  protected final String TEXT_285 = "}" + NL + "\t\treturn ((Data";
  protected final String TEXT_286 = ")data).";
  protected final String TEXT_287 = ";";
  protected final String TEXT_288 = NL + "\t\tif (getNodeId() > 0 && eContainer() == null) {" + NL + "\t\t\t";
  protected final String TEXT_289 = " ";
  protected final String TEXT_290 = " = (";
  protected final String TEXT_291 = ") ((INeo4emfResource) this.eResource()).getContainerOnDemand(this, ";
  protected final String TEXT_292 = ");" + NL + "\t\t\tbasicSet";
  protected final String TEXT_293 = "(";
  protected final String TEXT_294 = ",null);}" + NL + "\t\treturn (";
  protected final String TEXT_295 = ")eContainer();";
  protected final String TEXT_296 = NL + "\t\t";
  protected final String TEXT_297 = " ";
  protected final String TEXT_298 = " = (";
  protected final String TEXT_299 = ")eVirtualGet(";
  protected final String TEXT_300 = ", ";
  protected final String TEXT_301 = ");";
  protected final String TEXT_302 = NL + "\t\tif (((Data";
  protected final String TEXT_303 = ")data).";
  protected final String TEXT_304 = " == null && getNodeId()>-1)" + NL + "\t\t{" + NL + "\t\t\t((INeo4emfResource) this.eResource()).getOnDemand(this, ";
  protected final String TEXT_305 = ");" + NL + "\t\t}";
  protected final String TEXT_306 = "\t\t";
  protected final String TEXT_307 = NL + "\t\treturn (";
  protected final String TEXT_308 = ")eVirtualGet(";
  protected final String TEXT_309 = ", ";
  protected final String TEXT_310 = ");";
  protected final String TEXT_311 = NL + "\t\treturn (";
  protected final String TEXT_312 = " & Data";
  protected final String TEXT_313 = ".";
  protected final String TEXT_314 = "_EFLAG) != 0;";
  protected final String TEXT_315 = NL + "\t\treturn Data";
  protected final String TEXT_316 = ".";
  protected final String TEXT_317 = "_EFLAG_VALUES[(";
  protected final String TEXT_318 = " & Data";
  protected final String TEXT_319 = ".";
  protected final String TEXT_320 = "_EFLAG) >>> Data";
  protected final String TEXT_321 = ".";
  protected final String TEXT_322 = "_EFLAG_OFFSET];";
  protected final String TEXT_323 = NL + "\t\tif ( getNodeId()>-1 ) " + NL + "\t\t\teNotify(new ENotificationImpl(this, INeo4emfNotification.GET, ";
  protected final String TEXT_324 = ", null, null));" + NL + "\t\treturn ((Data";
  protected final String TEXT_325 = ")data).";
  protected final String TEXT_326 = ";";
  protected final String TEXT_327 = NL + "\t\t";
  protected final String TEXT_328 = " ";
  protected final String TEXT_329 = " = basicGet";
  protected final String TEXT_330 = "();" + NL + "\t\treturn ";
  protected final String TEXT_331 = " != null && ";
  protected final String TEXT_332 = ".eIsProxy() ? ";
  protected final String TEXT_333 = "eResolveProxy((";
  protected final String TEXT_334 = ")";
  protected final String TEXT_335 = ") : ";
  protected final String TEXT_336 = ";";
  protected final String TEXT_337 = NL + "\t\treturn new ";
  protected final String TEXT_338 = "((";
  protected final String TEXT_339 = ".Internal)((";
  protected final String TEXT_340 = ".Internal.Wrapper)get";
  protected final String TEXT_341 = "()).featureMap().";
  protected final String TEXT_342 = "list(";
  protected final String TEXT_343 = "));";
  protected final String TEXT_344 = NL + "\t\treturn (";
  protected final String TEXT_345 = ")get";
  protected final String TEXT_346 = "().";
  protected final String TEXT_347 = "list(";
  protected final String TEXT_348 = ");";
  protected final String TEXT_349 = NL + "\t\treturn ((";
  protected final String TEXT_350 = ".Internal.Wrapper)get";
  protected final String TEXT_351 = "()).featureMap().list(";
  protected final String TEXT_352 = ");";
  protected final String TEXT_353 = NL + "\t\treturn get";
  protected final String TEXT_354 = "().list(";
  protected final String TEXT_355 = ");";
  protected final String TEXT_356 = NL + "\t\treturn ";
  protected final String TEXT_357 = "(";
  protected final String TEXT_358 = "(";
  protected final String TEXT_359 = ")";
  protected final String TEXT_360 = "((";
  protected final String TEXT_361 = ".Internal.Wrapper)get";
  protected final String TEXT_362 = "()).featureMap().get(";
  protected final String TEXT_363 = ", true)";
  protected final String TEXT_364 = ").";
  protected final String TEXT_365 = "()";
  protected final String TEXT_366 = ";";
  protected final String TEXT_367 = NL + "\t\treturn ";
  protected final String TEXT_368 = "(";
  protected final String TEXT_369 = "(";
  protected final String TEXT_370 = ")";
  protected final String TEXT_371 = "get";
  protected final String TEXT_372 = "().get(";
  protected final String TEXT_373 = ", true)";
  protected final String TEXT_374 = ").";
  protected final String TEXT_375 = "()";
  protected final String TEXT_376 = ";";
  protected final String TEXT_377 = NL + "\t\t";
  protected final String TEXT_378 = NL + "\t\t";
  protected final String TEXT_379 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_380 = "' ";
  protected final String TEXT_381 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_382 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_383 = "EcoreEMap";
  protected final String TEXT_384 = "BasicFeatureMap";
  protected final String TEXT_385 = "EcoreEList";
  protected final String TEXT_386 = " should be used.";
  protected final String TEXT_387 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_388 = "\t" + NL + "\t}";
  protected final String TEXT_389 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_390 = NL + "\tpublic ";
  protected final String TEXT_391 = " basicGet";
  protected final String TEXT_392 = "()" + NL + "\t{";
  protected final String TEXT_393 = NL + "\t\treturn (";
  protected final String TEXT_394 = ")eDynamicGet(";
  protected final String TEXT_395 = ", ";
  protected final String TEXT_396 = ", false, ";
  protected final String TEXT_397 = ");";
  protected final String TEXT_398 = NL + "\t\treturn ";
  protected final String TEXT_399 = "(";
  protected final String TEXT_400 = "(";
  protected final String TEXT_401 = ")";
  protected final String TEXT_402 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_403 = ").";
  protected final String TEXT_404 = "()";
  protected final String TEXT_405 = ";";
  protected final String TEXT_406 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_407 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_408 = ")eInternalContainer();";
  protected final String TEXT_409 = NL + "\t\treturn (";
  protected final String TEXT_410 = ")eVirtualGet(";
  protected final String TEXT_411 = ");";
  protected final String TEXT_412 = NL + "\t\treturn data != null ? ((Data";
  protected final String TEXT_413 = ")data).";
  protected final String TEXT_414 = " : null;";
  protected final String TEXT_415 = NL + "\t\treturn (";
  protected final String TEXT_416 = ")((";
  protected final String TEXT_417 = ".Internal.Wrapper)get";
  protected final String TEXT_418 = "()).featureMap().get(";
  protected final String TEXT_419 = ", false);";
  protected final String TEXT_420 = NL + "\t\treturn (";
  protected final String TEXT_421 = ")get";
  protected final String TEXT_422 = "().get(";
  protected final String TEXT_423 = ", false);";
  protected final String TEXT_424 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_425 = "' ";
  protected final String TEXT_426 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_427 = NL + "\t}" + NL;
  protected final String TEXT_428 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_429 = NL + "\tpublic ";
  protected final String TEXT_430 = " basicSet";
  protected final String TEXT_431 = "(";
  protected final String TEXT_432 = " new";
  protected final String TEXT_433 = ", ";
  protected final String TEXT_434 = " msgs)" + NL + "\t{" + NL + "\t";
  protected final String TEXT_435 = NL + "\t\tif (data==null) data =  new Data";
  protected final String TEXT_436 = "();" + NL + "\t";
  protected final String TEXT_437 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_438 = ")new";
  protected final String TEXT_439 = ", ";
  protected final String TEXT_440 = ", msgs);";
  protected final String TEXT_441 = NL + "\t\treturn msgs;";
  protected final String TEXT_442 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_443 = ")new";
  protected final String TEXT_444 = ", ";
  protected final String TEXT_445 = ", msgs);";
  protected final String TEXT_446 = NL + "\t\treturn msgs;";
  protected final String TEXT_447 = NL + "\t\tObject old";
  protected final String TEXT_448 = " = eVirtualSet(";
  protected final String TEXT_449 = ", new";
  protected final String TEXT_450 = ");";
  protected final String TEXT_451 = NL + "\t\t";
  protected final String TEXT_452 = " old";
  protected final String TEXT_453 = " = ((Data";
  protected final String TEXT_454 = ")data).";
  protected final String TEXT_455 = ";" + NL + "\t\t((Data";
  protected final String TEXT_456 = ")data).";
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
  protected final String TEXT_529 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY2" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_530 = NL + "\tvoid set";
  protected final String TEXT_531 = "(";
  protected final String TEXT_532 = " value);" + NL;
  protected final String TEXT_533 = NL + "\tpublic void set";
  protected final String TEXT_534 = "_";
  protected final String TEXT_535 = "(";
  protected final String TEXT_536 = " ";
  protected final String TEXT_537 = ")" + NL + "\t{";
  protected final String TEXT_538 = NL + "\t";
  protected final String TEXT_539 = NL + "\t\tif (data==null) data =  new Data";
  protected final String TEXT_540 = "();" + NL + "\t\t";
  protected final String TEXT_541 = NL + "\t\telse if (!(data instanceof Data";
  protected final String TEXT_542 = ")) data = new Data";
  protected final String TEXT_543 = "((Data";
  protected final String TEXT_544 = ")data);" + NL + "\t";
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
  protected final String TEXT_597 = " != ((Data";
  protected final String TEXT_598 = ")data).";
  protected final String TEXT_599 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_600 = " msgs = null;" + NL + "\t\t\tif (((Data";
  protected final String TEXT_601 = ")data).";
  protected final String TEXT_602 = " != null)";
  protected final String TEXT_603 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_604 = ") ((Data";
  protected final String TEXT_605 = ")data).";
  protected final String TEXT_606 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_607 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_608 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_609 = ")new";
  protected final String TEXT_610 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_611 = ", null, msgs);";
  protected final String TEXT_612 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_613 = ") ((Data";
  protected final String TEXT_614 = ")data).";
  protected final String TEXT_615 = ").eInverseRemove(this, ";
  protected final String TEXT_616 = ", ";
  protected final String TEXT_617 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_618 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_619 = ")new";
  protected final String TEXT_620 = ").eInverseAdd(this, ";
  protected final String TEXT_621 = ", ";
  protected final String TEXT_622 = ".class, msgs);";
  protected final String TEXT_623 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_624 = "(";
  protected final String TEXT_625 = "new";
  protected final String TEXT_626 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_627 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_628 = NL + "\t\t\tboolean old";
  protected final String TEXT_629 = "ESet = eVirtualIsSet(";
  protected final String TEXT_630 = ");";
  protected final String TEXT_631 = NL + "\t\t\tboolean old";
  protected final String TEXT_632 = "ESet = (";
  protected final String TEXT_633 = " & ";
  protected final String TEXT_634 = "_ESETFLAG) != 0;";
  protected final String TEXT_635 = NL + "\t\t\t";
  protected final String TEXT_636 = " |= ";
  protected final String TEXT_637 = "_ESETFLAG;";
  protected final String TEXT_638 = NL + "\t\t\tboolean old";
  protected final String TEXT_639 = "ESet = ";
  protected final String TEXT_640 = "ESet;";
  protected final String TEXT_641 = NL + "\t\t\t";
  protected final String TEXT_642 = "ESet = true;";
  protected final String TEXT_643 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_644 = "(this, ";
  protected final String TEXT_645 = ".SET, ";
  protected final String TEXT_646 = ", new";
  protected final String TEXT_647 = ", new";
  protected final String TEXT_648 = ", !old";
  protected final String TEXT_649 = "ESet));";
  protected final String TEXT_650 = NL + "\t\t}";
  protected final String TEXT_651 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_652 = "(this, ";
  protected final String TEXT_653 = ".SET, ";
  protected final String TEXT_654 = ", new";
  protected final String TEXT_655 = ", new";
  protected final String TEXT_656 = "));";
  protected final String TEXT_657 = NL + "\t\t";
  protected final String TEXT_658 = " old";
  protected final String TEXT_659 = " = (";
  protected final String TEXT_660 = " & Data";
  protected final String TEXT_661 = ".";
  protected final String TEXT_662 = "_EFLAG) != 0;";
  protected final String TEXT_663 = NL + "\t\t";
  protected final String TEXT_664 = " old";
  protected final String TEXT_665 = " = ";
  protected final String TEXT_666 = "_EFLAG_VALUES[(";
  protected final String TEXT_667 = " & Data";
  protected final String TEXT_668 = ".";
  protected final String TEXT_669 = "_EFLAG) >>> ";
  protected final String TEXT_670 = "_EFLAG_OFFSET];";
  protected final String TEXT_671 = NL + "\t\tif (new";
  protected final String TEXT_672 = ") ";
  protected final String TEXT_673 = " |= Data";
  protected final String TEXT_674 = ".";
  protected final String TEXT_675 = "_EFLAG; else ";
  protected final String TEXT_676 = " &= ~Data";
  protected final String TEXT_677 = ".";
  protected final String TEXT_678 = "_EFLAG;";
  protected final String TEXT_679 = NL + "\t\tif (new";
  protected final String TEXT_680 = " == null) new";
  protected final String TEXT_681 = " = ";
  protected final String TEXT_682 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_683 = " = ";
  protected final String TEXT_684 = " & ~Data";
  protected final String TEXT_685 = ".";
  protected final String TEXT_686 = "_EFLAG | ";
  protected final String TEXT_687 = "new";
  protected final String TEXT_688 = ".ordinal()";
  protected final String TEXT_689 = ".VALUES.indexOf(new";
  protected final String TEXT_690 = ")";
  protected final String TEXT_691 = " << ";
  protected final String TEXT_692 = "_EFLAG_OFFSET;";
  protected final String TEXT_693 = NL + "\t\t";
  protected final String TEXT_694 = " old";
  protected final String TEXT_695 = " = ((Data";
  protected final String TEXT_696 = ")data).";
  protected final String TEXT_697 = ";";
  protected final String TEXT_698 = NL + "\t\t";
  protected final String TEXT_699 = " ";
  protected final String TEXT_700 = " = new";
  protected final String TEXT_701 = " == null ? ";
  protected final String TEXT_702 = " : new";
  protected final String TEXT_703 = ";";
  protected final String TEXT_704 = NL + "\t\t((Data";
  protected final String TEXT_705 = ")data).";
  protected final String TEXT_706 = " = new";
  protected final String TEXT_707 = " == null ? ((Data";
  protected final String TEXT_708 = ")data).";
  protected final String TEXT_709 = " : new";
  protected final String TEXT_710 = ";";
  protected final String TEXT_711 = NL + "\t\t";
  protected final String TEXT_712 = " ";
  protected final String TEXT_713 = " = ";
  protected final String TEXT_714 = "new";
  protected final String TEXT_715 = ";";
  protected final String TEXT_716 = NL + "\t\t((Data";
  protected final String TEXT_717 = ")data).";
  protected final String TEXT_718 = " = ";
  protected final String TEXT_719 = "new";
  protected final String TEXT_720 = ";";
  protected final String TEXT_721 = NL + "\t\tObject old";
  protected final String TEXT_722 = " = eVirtualSet(";
  protected final String TEXT_723 = ", ";
  protected final String TEXT_724 = ");";
  protected final String TEXT_725 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_726 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_727 = NL + "\t\tboolean old";
  protected final String TEXT_728 = "ESet = (";
  protected final String TEXT_729 = " & ";
  protected final String TEXT_730 = "_ESETFLAG) != 0;";
  protected final String TEXT_731 = NL + "\t\t";
  protected final String TEXT_732 = " |= ";
  protected final String TEXT_733 = "_ESETFLAG;";
  protected final String TEXT_734 = NL + "\t\tboolean old";
  protected final String TEXT_735 = "ESet = ";
  protected final String TEXT_736 = "ESet;";
  protected final String TEXT_737 = NL + "\t\t";
  protected final String TEXT_738 = "ESet = true;";
  protected final String TEXT_739 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_740 = "(this, ";
  protected final String TEXT_741 = ".SET, ";
  protected final String TEXT_742 = ", ";
  protected final String TEXT_743 = "isSetChange ? ";
  protected final String TEXT_744 = " : old";
  protected final String TEXT_745 = "old";
  protected final String TEXT_746 = ", ";
  protected final String TEXT_747 = "new";
  protected final String TEXT_748 = ", ";
  protected final String TEXT_749 = "isSetChange";
  protected final String TEXT_750 = "!old";
  protected final String TEXT_751 = "ESet";
  protected final String TEXT_752 = "));";
  protected final String TEXT_753 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_754 = "(this, ";
  protected final String TEXT_755 = ".SET, ";
  protected final String TEXT_756 = ", ";
  protected final String TEXT_757 = "old";
  protected final String TEXT_758 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_759 = " : old";
  protected final String TEXT_760 = "old";
  protected final String TEXT_761 = ", ";
  protected final String TEXT_762 = "new";
  protected final String TEXT_763 = "((Data";
  protected final String TEXT_764 = ")data).";
  protected final String TEXT_765 = "));";
  protected final String TEXT_766 = NL + "\t\t((";
  protected final String TEXT_767 = ".Internal)((";
  protected final String TEXT_768 = ".Internal.Wrapper)get";
  protected final String TEXT_769 = "()).featureMap()).set(";
  protected final String TEXT_770 = ", ";
  protected final String TEXT_771 = "new ";
  protected final String TEXT_772 = "(";
  protected final String TEXT_773 = "new";
  protected final String TEXT_774 = ")";
  protected final String TEXT_775 = ");";
  protected final String TEXT_776 = NL + "\t\t((";
  protected final String TEXT_777 = ".Internal)get";
  protected final String TEXT_778 = "()).set(";
  protected final String TEXT_779 = ", ";
  protected final String TEXT_780 = "new ";
  protected final String TEXT_781 = "(";
  protected final String TEXT_782 = "new";
  protected final String TEXT_783 = ")";
  protected final String TEXT_784 = ");";
  protected final String TEXT_785 = NL + "\t\t";
  protected final String TEXT_786 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_787 = "' ";
  protected final String TEXT_788 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_789 = NL + "\t}" + NL;
  protected final String TEXT_790 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY3" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_791 = NL + "\tpublic ";
  protected final String TEXT_792 = " basicUnset";
  protected final String TEXT_793 = "(";
  protected final String TEXT_794 = " msgs)" + NL + "\t{";
  protected final String TEXT_795 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_796 = ")";
  protected final String TEXT_797 = "basicGet";
  protected final String TEXT_798 = "(), ";
  protected final String TEXT_799 = ", msgs);";
  protected final String TEXT_800 = "Object old";
  protected final String TEXT_801 = " = ";
  protected final String TEXT_802 = "eVirtualUnset(";
  protected final String TEXT_803 = ");";
  protected final String TEXT_804 = NL + "\t\t";
  protected final String TEXT_805 = " old";
  protected final String TEXT_806 = " = ";
  protected final String TEXT_807 = ";";
  protected final String TEXT_808 = NL + "\t\t";
  protected final String TEXT_809 = " = null;";
  protected final String TEXT_810 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_811 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_812 = NL + "\t\tboolean old";
  protected final String TEXT_813 = "ESet = (";
  protected final String TEXT_814 = " & ";
  protected final String TEXT_815 = "_ESETFLAG) != 0;";
  protected final String TEXT_816 = NL + "\t\t";
  protected final String TEXT_817 = " &= ~";
  protected final String TEXT_818 = "_ESETFLAG;";
  protected final String TEXT_819 = NL + "\t\tboolean old";
  protected final String TEXT_820 = "ESet = ";
  protected final String TEXT_821 = "ESet;";
  protected final String TEXT_822 = NL + "\t\t";
  protected final String TEXT_823 = "ESet = false;";
  protected final String TEXT_824 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_825 = " notification = new ";
  protected final String TEXT_826 = "(this, ";
  protected final String TEXT_827 = ".UNSET, ";
  protected final String TEXT_828 = ", ";
  protected final String TEXT_829 = "isSetChange ? old";
  protected final String TEXT_830 = " : null";
  protected final String TEXT_831 = "old";
  protected final String TEXT_832 = ", null, ";
  protected final String TEXT_833 = "isSetChange";
  protected final String TEXT_834 = "old";
  protected final String TEXT_835 = "ESet";
  protected final String TEXT_836 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_837 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_838 = "' ";
  protected final String TEXT_839 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_840 = NL + "\t}" + NL;
  protected final String TEXT_841 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_842 = "#";
  protected final String TEXT_843 = " <em>";
  protected final String TEXT_844 = "</em>}' ";
  protected final String TEXT_845 = ".";
  protected final String TEXT_846 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY4" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_847 = NL + "\t * @see #isSet";
  protected final String TEXT_848 = "()";
  protected final String TEXT_849 = NL + "\t * @see #";
  protected final String TEXT_850 = "()";
  protected final String TEXT_851 = NL + "\t * @see #set";
  protected final String TEXT_852 = "(";
  protected final String TEXT_853 = ")";
  protected final String TEXT_854 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_855 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY5" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_856 = NL + "\tvoid unset";
  protected final String TEXT_857 = "();" + NL;
  protected final String TEXT_858 = NL + "\tpublic void unset";
  protected final String TEXT_859 = "_";
  protected final String TEXT_860 = "()" + NL + "\t{";
  protected final String TEXT_861 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_862 = ", ";
  protected final String TEXT_863 = ");";
  protected final String TEXT_864 = NL + "\t\teUnset(";
  protected final String TEXT_865 = ");";
  protected final String TEXT_866 = NL + "\t\t";
  protected final String TEXT_867 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_868 = NL + "\t\t";
  protected final String TEXT_869 = " ";
  protected final String TEXT_870 = " = (";
  protected final String TEXT_871 = ")eVirtualGet(";
  protected final String TEXT_872 = ");";
  protected final String TEXT_873 = NL + "\t\tif (((Data";
  protected final String TEXT_874 = ")data).";
  protected final String TEXT_875 = " != null) ((";
  protected final String TEXT_876 = ".Unsettable";
  protected final String TEXT_877 = ")";
  protected final String TEXT_878 = ").unset();";
  protected final String TEXT_879 = NL + "\t\t";
  protected final String TEXT_880 = " ";
  protected final String TEXT_881 = " = (";
  protected final String TEXT_882 = ")eVirtualGet(";
  protected final String TEXT_883 = ");";
  protected final String TEXT_884 = NL + "\t\tif (((Data";
  protected final String TEXT_885 = ")data).";
  protected final String TEXT_886 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_887 = " msgs = null;";
  protected final String TEXT_888 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_889 = ")((Data";
  protected final String TEXT_890 = ")data).";
  protected final String TEXT_891 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_892 = ", null, msgs);";
  protected final String TEXT_893 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_894 = ")((Data";
  protected final String TEXT_895 = ")data).";
  protected final String TEXT_896 = ").eInverseRemove(this, ";
  protected final String TEXT_897 = ", ";
  protected final String TEXT_898 = ".class, msgs);";
  protected final String TEXT_899 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_900 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_901 = NL + "\t\t\tboolean old";
  protected final String TEXT_902 = "ESet = eVirtualIsSet(";
  protected final String TEXT_903 = ");";
  protected final String TEXT_904 = NL + "\t\t\tboolean old";
  protected final String TEXT_905 = "ESet = (";
  protected final String TEXT_906 = " & ";
  protected final String TEXT_907 = "_ESETFLAG) != 0;";
  protected final String TEXT_908 = NL + "\t\t\t";
  protected final String TEXT_909 = " &= ~";
  protected final String TEXT_910 = "_ESETFLAG;";
  protected final String TEXT_911 = NL + "\t\t\tboolean old";
  protected final String TEXT_912 = "ESet = ";
  protected final String TEXT_913 = "ESet;";
  protected final String TEXT_914 = NL + "\t\t\t";
  protected final String TEXT_915 = "ESet = false;";
  protected final String TEXT_916 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_917 = "(this, ";
  protected final String TEXT_918 = ".UNSET, ";
  protected final String TEXT_919 = ", null, null, old";
  protected final String TEXT_920 = "ESet));";
  protected final String TEXT_921 = NL + "\t\t}";
  protected final String TEXT_922 = NL + "\t\t";
  protected final String TEXT_923 = " old";
  protected final String TEXT_924 = " = (";
  protected final String TEXT_925 = " & ";
  protected final String TEXT_926 = "_EFLAG) != 0;";
  protected final String TEXT_927 = NL + "\t\t";
  protected final String TEXT_928 = " old";
  protected final String TEXT_929 = " = ";
  protected final String TEXT_930 = "_EFLAG_VALUES[(";
  protected final String TEXT_931 = " & ";
  protected final String TEXT_932 = "_EFLAG) >>> ";
  protected final String TEXT_933 = "_EFLAG_OFFSET];";
  protected final String TEXT_934 = NL + "\t\tObject old";
  protected final String TEXT_935 = " = eVirtualUnset(";
  protected final String TEXT_936 = ");";
  protected final String TEXT_937 = NL + "\t\t";
  protected final String TEXT_938 = " old";
  protected final String TEXT_939 = " = ";
  protected final String TEXT_940 = ";";
  protected final String TEXT_941 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_942 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_943 = NL + "\t\tboolean old";
  protected final String TEXT_944 = "ESet = (";
  protected final String TEXT_945 = " & ";
  protected final String TEXT_946 = "_ESETFLAG) != 0;";
  protected final String TEXT_947 = NL + "\t\tboolean old";
  protected final String TEXT_948 = "ESet = ";
  protected final String TEXT_949 = "ESet;";
  protected final String TEXT_950 = NL + "\t\t";
  protected final String TEXT_951 = " = null;";
  protected final String TEXT_952 = NL + "\t\t";
  protected final String TEXT_953 = " &= ~";
  protected final String TEXT_954 = "_ESETFLAG;";
  protected final String TEXT_955 = NL + "\t\t";
  protected final String TEXT_956 = "ESet = false;";
  protected final String TEXT_957 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_958 = "(this, ";
  protected final String TEXT_959 = ".UNSET, ";
  protected final String TEXT_960 = ", ";
  protected final String TEXT_961 = "isSetChange ? old";
  protected final String TEXT_962 = " : null";
  protected final String TEXT_963 = "old";
  protected final String TEXT_964 = ", null, ";
  protected final String TEXT_965 = "isSetChange";
  protected final String TEXT_966 = "old";
  protected final String TEXT_967 = "ESet";
  protected final String TEXT_968 = "));";
  protected final String TEXT_969 = NL + "\t\tif (";
  protected final String TEXT_970 = ") ";
  protected final String TEXT_971 = " |= ";
  protected final String TEXT_972 = "_EFLAG; else ";
  protected final String TEXT_973 = " &= ~";
  protected final String TEXT_974 = "_EFLAG;";
  protected final String TEXT_975 = NL + "\t\t";
  protected final String TEXT_976 = " = ";
  protected final String TEXT_977 = " & ~";
  protected final String TEXT_978 = "_EFLAG | ";
  protected final String TEXT_979 = "_EFLAG_DEFAULT;";
  protected final String TEXT_980 = NL + "\t\t";
  protected final String TEXT_981 = " = ";
  protected final String TEXT_982 = ";";
  protected final String TEXT_983 = NL + "\t\t";
  protected final String TEXT_984 = " &= ~";
  protected final String TEXT_985 = "_ESETFLAG;";
  protected final String TEXT_986 = NL + "\t\t";
  protected final String TEXT_987 = "ESet = false;";
  protected final String TEXT_988 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_989 = "(this, ";
  protected final String TEXT_990 = ".UNSET, ";
  protected final String TEXT_991 = ", ";
  protected final String TEXT_992 = "isSetChange ? old";
  protected final String TEXT_993 = " : ";
  protected final String TEXT_994 = "old";
  protected final String TEXT_995 = ", ";
  protected final String TEXT_996 = ", ";
  protected final String TEXT_997 = "isSetChange";
  protected final String TEXT_998 = "old";
  protected final String TEXT_999 = "ESet";
  protected final String TEXT_1000 = "));";
  protected final String TEXT_1001 = NL + "\t\t((";
  protected final String TEXT_1002 = ".Internal)((";
  protected final String TEXT_1003 = ".Internal.Wrapper)get";
  protected final String TEXT_1004 = "()).featureMap()).clear(";
  protected final String TEXT_1005 = ");";
  protected final String TEXT_1006 = NL + "\t\t((";
  protected final String TEXT_1007 = ".Internal)get";
  protected final String TEXT_1008 = "()).clear(";
  protected final String TEXT_1009 = ");";
  protected final String TEXT_1010 = NL + "\t\t";
  protected final String TEXT_1011 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1012 = "' ";
  protected final String TEXT_1013 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1014 = NL + "\t}" + NL;
  protected final String TEXT_1015 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1016 = "#";
  protected final String TEXT_1017 = " <em>";
  protected final String TEXT_1018 = "</em>}' ";
  protected final String TEXT_1019 = " is set.";
  protected final String TEXT_1020 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY6" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1021 = "</em>' ";
  protected final String TEXT_1022 = " is set.";
  protected final String TEXT_1023 = NL + "\t * @see #unset";
  protected final String TEXT_1024 = "()";
  protected final String TEXT_1025 = NL + "\t * @see #";
  protected final String TEXT_1026 = "()";
  protected final String TEXT_1027 = NL + "\t * @see #set";
  protected final String TEXT_1028 = "(";
  protected final String TEXT_1029 = ")";
  protected final String TEXT_1030 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1031 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY7" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1032 = NL + "\tboolean isSet";
  protected final String TEXT_1033 = "();" + NL;
  protected final String TEXT_1034 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1035 = "_";
  protected final String TEXT_1036 = "()" + NL + "\t{";
  protected final String TEXT_1037 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1038 = ", ";
  protected final String TEXT_1039 = ");";
  protected final String TEXT_1040 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1041 = ");";
  protected final String TEXT_1042 = NL + "\t\treturn ";
  protected final String TEXT_1043 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1044 = NL + "\t\t";
  protected final String TEXT_1045 = " ";
  protected final String TEXT_1046 = " = (";
  protected final String TEXT_1047 = ")eVirtualGet(";
  protected final String TEXT_1048 = ");";
  protected final String TEXT_1049 = NL + "\t\treturn ";
  protected final String TEXT_1050 = " != null && ((";
  protected final String TEXT_1051 = ".Unsettable";
  protected final String TEXT_1052 = ")";
  protected final String TEXT_1053 = ").isSet();";
  protected final String TEXT_1054 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1055 = ");";
  protected final String TEXT_1056 = NL + "\t\treturn (";
  protected final String TEXT_1057 = " & ";
  protected final String TEXT_1058 = "_ESETFLAG) != 0;";
  protected final String TEXT_1059 = NL + "\t\treturn ";
  protected final String TEXT_1060 = "ESet;";
  protected final String TEXT_1061 = NL + "\t\treturn !((";
  protected final String TEXT_1062 = ".Internal)((";
  protected final String TEXT_1063 = ".Internal.Wrapper)get";
  protected final String TEXT_1064 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1065 = ");";
  protected final String TEXT_1066 = NL + "\t\treturn !((";
  protected final String TEXT_1067 = ".Internal)get";
  protected final String TEXT_1068 = "()).isEmpty(";
  protected final String TEXT_1069 = ");";
  protected final String TEXT_1070 = NL + "\t\t";
  protected final String TEXT_1071 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1072 = "' ";
  protected final String TEXT_1073 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1074 = NL + "\t}" + NL;
  protected final String TEXT_1075 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1076 = "(";
  protected final String TEXT_1077 = ") <em>";
  protected final String TEXT_1078 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1079 = "(";
  protected final String TEXT_1080 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1081 = " ";
  protected final String TEXT_1082 = "__EEXPRESSION = \"";
  protected final String TEXT_1083 = "\";";
  protected final String TEXT_1084 = NL;
  protected final String TEXT_1085 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1086 = "(";
  protected final String TEXT_1087 = ") <em>";
  protected final String TEXT_1088 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1089 = "(";
  protected final String TEXT_1090 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1091 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1092 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1093 = ".Internal)";
  protected final String TEXT_1094 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1095 = NL + "\t/**";
  protected final String TEXT_1096 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY10" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1097 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1098 = NL + "\t * ";
  protected final String TEXT_1099 = NL + "\t * @param ";
  protected final String TEXT_1100 = NL + "\t *   ";
  protected final String TEXT_1101 = NL + "\t * @param ";
  protected final String TEXT_1102 = " ";
  protected final String TEXT_1103 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1104 = NL + "\t * @model ";
  protected final String TEXT_1105 = NL + "\t *        ";
  protected final String TEXT_1106 = NL + "\t * @model";
  protected final String TEXT_1107 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1108 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY11" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1109 = NL + "\t";
  protected final String TEXT_1110 = " ";
  protected final String TEXT_1111 = "(";
  protected final String TEXT_1112 = ")";
  protected final String TEXT_1113 = ";" + NL;
  protected final String TEXT_1114 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1115 = NL + "\tpublic ";
  protected final String TEXT_1116 = " ";
  protected final String TEXT_1117 = "(";
  protected final String TEXT_1118 = ")";
  protected final String TEXT_1119 = NL + "\t{";
  protected final String TEXT_1120 = NL + "\t\t";
  protected final String TEXT_1121 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1122 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1123 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1124 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1125 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1126 = "\",";
  protected final String TEXT_1127 = NL + "\t\t\t\t ";
  protected final String TEXT_1128 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1129 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1130 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1131 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1132 = ".";
  protected final String TEXT_1133 = ");";
  protected final String TEXT_1134 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1135 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1136 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1137 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1138 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1139 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1140 = ".";
  protected final String TEXT_1141 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1142 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1143 = "\", ";
  protected final String TEXT_1144 = ".getObjectLabel(this, ";
  protected final String TEXT_1145 = ") }),";
  protected final String TEXT_1146 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1147 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1148 = NL + "\t\t\t";
  protected final String TEXT_1149 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1150 = "new ";
  protected final String TEXT_1151 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1152 = ", ";
  protected final String TEXT_1153 = ")";
  protected final String TEXT_1154 = "null";
  protected final String TEXT_1155 = ");";
  protected final String TEXT_1156 = NL + "\t\t\treturn ";
  protected final String TEXT_1157 = "(";
  protected final String TEXT_1158 = "(";
  protected final String TEXT_1159 = ")";
  protected final String TEXT_1160 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1161 = "new ";
  protected final String TEXT_1162 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1163 = ", ";
  protected final String TEXT_1164 = ")";
  protected final String TEXT_1165 = "null";
  protected final String TEXT_1166 = ")";
  protected final String TEXT_1167 = ").";
  protected final String TEXT_1168 = "()";
  protected final String TEXT_1169 = ";";
  protected final String TEXT_1170 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1171 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1172 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1173 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1174 = NL + "\t}" + NL;
  protected final String TEXT_1175 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY12" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1176 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1177 = NL + "\t@Override";
  protected final String TEXT_1178 = NL + "\tpublic ";
  protected final String TEXT_1179 = " eInverseAdd(";
  protected final String TEXT_1180 = " otherEnd, int featureID, ";
  protected final String TEXT_1181 = " msgs)" + NL + "\t{" + NL + "\t\tif (data==null) data = new Data";
  protected final String TEXT_1182 = "();" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1183 = ")" + NL + "\t\t{";
  protected final String TEXT_1184 = NL + "\t\t\tcase ";
  protected final String TEXT_1185 = ":";
  protected final String TEXT_1186 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1187 = "(";
  protected final String TEXT_1188 = ".InternalMapView";
  protected final String TEXT_1189 = ")";
  protected final String TEXT_1190 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1191 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1192 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1193 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1194 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1195 = "((";
  protected final String TEXT_1196 = ")otherEnd, msgs);";
  protected final String TEXT_1197 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1198 = ", msgs);";
  protected final String TEXT_1199 = NL + "\t\t\t\t";
  protected final String TEXT_1200 = " ";
  protected final String TEXT_1201 = " = (";
  protected final String TEXT_1202 = ")eVirtualGet(";
  protected final String TEXT_1203 = ");";
  protected final String TEXT_1204 = NL + "\t\t\t\t";
  protected final String TEXT_1205 = " ";
  protected final String TEXT_1206 = " = ";
  protected final String TEXT_1207 = "basicGet";
  protected final String TEXT_1208 = "();";
  protected final String TEXT_1209 = NL + "\t\t\t\tif (((Data";
  protected final String TEXT_1210 = ")data).";
  protected final String TEXT_1211 = " != null)";
  protected final String TEXT_1212 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1213 = ")((Data";
  protected final String TEXT_1214 = ")data).";
  protected final String TEXT_1215 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1216 = ", null, msgs);";
  protected final String TEXT_1217 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1218 = ")((Data";
  protected final String TEXT_1219 = ")data).";
  protected final String TEXT_1220 = ").eInverseRemove(this, ";
  protected final String TEXT_1221 = ", ";
  protected final String TEXT_1222 = ".class, msgs);";
  protected final String TEXT_1223 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1224 = "((";
  protected final String TEXT_1225 = ")otherEnd, msgs);";
  protected final String TEXT_1226 = NL + "\t\t}";
  protected final String TEXT_1227 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1228 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1229 = NL + "\t}" + NL;
  protected final String TEXT_1230 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY13" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1231 = NL + "\t@Override";
  protected final String TEXT_1232 = NL + "\tpublic ";
  protected final String TEXT_1233 = " eInverseRemove(";
  protected final String TEXT_1234 = " otherEnd, int featureID, ";
  protected final String TEXT_1235 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1236 = ")" + NL + "\t\t{";
  protected final String TEXT_1237 = NL + "\t\t\tcase ";
  protected final String TEXT_1238 = ":";
  protected final String TEXT_1239 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1240 = ")((";
  protected final String TEXT_1241 = ".InternalMapView";
  protected final String TEXT_1242 = ")";
  protected final String TEXT_1243 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1244 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1245 = ")((";
  protected final String TEXT_1246 = ".Internal.Wrapper)";
  protected final String TEXT_1247 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1248 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1249 = ")";
  protected final String TEXT_1250 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1251 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1252 = ", msgs);";
  protected final String TEXT_1253 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1254 = "(msgs);";
  protected final String TEXT_1255 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1256 = "(null, msgs);";
  protected final String TEXT_1257 = NL + "\t\t}";
  protected final String TEXT_1258 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1259 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1260 = NL + "\t}" + NL;
  protected final String TEXT_1261 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY14" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1262 = NL + "\t@Override";
  protected final String TEXT_1263 = NL + "\tpublic ";
  protected final String TEXT_1264 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1265 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1266 = ")" + NL + "\t\t{";
  protected final String TEXT_1267 = NL + "\t\t\tcase ";
  protected final String TEXT_1268 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1269 = ", ";
  protected final String TEXT_1270 = ".class, msgs);";
  protected final String TEXT_1271 = NL + "\t\t}";
  protected final String TEXT_1272 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1273 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1274 = NL + "\t}" + NL;
  protected final String TEXT_1275 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY15" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1276 = NL + "\t@Override";
  protected final String TEXT_1277 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1278 = ")" + NL + "\t\t{";
  protected final String TEXT_1279 = NL + "\t\t\tcase ";
  protected final String TEXT_1280 = ":";
  protected final String TEXT_1281 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1282 = "();";
  protected final String TEXT_1283 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1284 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1285 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1286 = "(";
  protected final String TEXT_1287 = "());";
  protected final String TEXT_1288 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1289 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1290 = "();";
  protected final String TEXT_1291 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1292 = ".InternalMapView";
  protected final String TEXT_1293 = ")";
  protected final String TEXT_1294 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1295 = "();";
  protected final String TEXT_1296 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1297 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1298 = "().map();";
  protected final String TEXT_1299 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1300 = ".Internal.Wrapper)";
  protected final String TEXT_1301 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1302 = "();";
  protected final String TEXT_1303 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1304 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1305 = ".Internal)";
  protected final String TEXT_1306 = "()).getWrapper();";
  protected final String TEXT_1307 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1308 = "();";
  protected final String TEXT_1309 = NL + "\t\t}";
  protected final String TEXT_1310 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1311 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1312 = NL + "\t}" + NL;
  protected final String TEXT_1313 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY16" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1314 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1315 = NL + "\t@Override";
  protected final String TEXT_1316 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1317 = ")" + NL + "\t\t{";
  protected final String TEXT_1318 = NL + "\t\t\tcase ";
  protected final String TEXT_1319 = ":";
  protected final String TEXT_1320 = NL + "\t\t\t\t((";
  protected final String TEXT_1321 = ".Internal)((";
  protected final String TEXT_1322 = ".Internal.Wrapper)";
  protected final String TEXT_1323 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1324 = NL + "\t\t\t\t((";
  protected final String TEXT_1325 = ".Internal)";
  protected final String TEXT_1326 = "()).set(newValue);";
  protected final String TEXT_1327 = NL + "\t\t\t\t((";
  protected final String TEXT_1328 = ".Setting)((";
  protected final String TEXT_1329 = ".InternalMapView";
  protected final String TEXT_1330 = ")";
  protected final String TEXT_1331 = "()).eMap()).set(newValue);";
  protected final String TEXT_1332 = NL + "\t\t\t\t((";
  protected final String TEXT_1333 = ".Setting)";
  protected final String TEXT_1334 = "()).set(newValue);";
  protected final String TEXT_1335 = NL + "\t\t\t\t";
  protected final String TEXT_1336 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1337 = "().addAll((";
  protected final String TEXT_1338 = "<? extends ";
  protected final String TEXT_1339 = ">";
  protected final String TEXT_1340 = ")newValue);";
  protected final String TEXT_1341 = NL + "\t\t\t\tset";
  protected final String TEXT_1342 = "(((";
  protected final String TEXT_1343 = ")newValue).";
  protected final String TEXT_1344 = "());";
  protected final String TEXT_1345 = NL + "\t\t\t\tset";
  protected final String TEXT_1346 = "(";
  protected final String TEXT_1347 = "(";
  protected final String TEXT_1348 = ")";
  protected final String TEXT_1349 = "newValue);";
  protected final String TEXT_1350 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1351 = NL + "\t\t}";
  protected final String TEXT_1352 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1353 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1354 = NL + "\t}" + NL;
  protected final String TEXT_1355 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY17" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1356 = NL + "\t@Override";
  protected final String TEXT_1357 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1358 = ")" + NL + "\t\t{";
  protected final String TEXT_1359 = NL + "\t\t\tcase ";
  protected final String TEXT_1360 = ":";
  protected final String TEXT_1361 = NL + "\t\t\t\t((";
  protected final String TEXT_1362 = ".Internal.Wrapper)";
  protected final String TEXT_1363 = "()).featureMap().clear();";
  protected final String TEXT_1364 = NL + "\t\t\t\t";
  protected final String TEXT_1365 = "().clear();";
  protected final String TEXT_1366 = NL + "\t\t\t\tunset";
  protected final String TEXT_1367 = "();";
  protected final String TEXT_1368 = NL + "\t\t\t\tset";
  protected final String TEXT_1369 = "((";
  protected final String TEXT_1370 = ")null);";
  protected final String TEXT_1371 = NL + "\t\t\t\t";
  protected final String TEXT_1372 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1373 = NL + "\t\t\t\tset";
  protected final String TEXT_1374 = "(Data";
  protected final String TEXT_1375 = ".";
  protected final String TEXT_1376 = ");";
  protected final String TEXT_1377 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1378 = NL + "\t\t}";
  protected final String TEXT_1379 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1380 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1381 = NL + "\t}" + NL;
  protected final String TEXT_1382 = NL + "/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY18" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1383 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1384 = NL + "\t@Override";
  protected final String TEXT_1385 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1386 = ")" + NL + "\t\t{";
  protected final String TEXT_1387 = NL + "\t\t\tcase ";
  protected final String TEXT_1388 = ":";
  protected final String TEXT_1389 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1390 = "();";
  protected final String TEXT_1391 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1392 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1393 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1394 = ".Internal.Wrapper)";
  protected final String TEXT_1395 = "()).featureMap().isEmpty();";
  protected final String TEXT_1396 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1397 = "() != null && !";
  protected final String TEXT_1398 = "().featureMap().isEmpty();";
  protected final String TEXT_1399 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1400 = "() != null && !";
  protected final String TEXT_1401 = "().isEmpty();";
  protected final String TEXT_1402 = NL + "\t\t\t\t";
  protected final String TEXT_1403 = " ";
  protected final String TEXT_1404 = " = (";
  protected final String TEXT_1405 = ")eVirtualGet(";
  protected final String TEXT_1406 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1407 = " != null && !";
  protected final String TEXT_1408 = ".isEmpty();";
  protected final String TEXT_1409 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1410 = "().isEmpty();";
  protected final String TEXT_1411 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1412 = "();";
  protected final String TEXT_1413 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1414 = "() != null;";
  protected final String TEXT_1415 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1416 = ") != null;";
  protected final String TEXT_1417 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1418 = "() != null;";
  protected final String TEXT_1419 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1420 = "() != null;";
  protected final String TEXT_1421 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1422 = ") != null;";
  protected final String TEXT_1423 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1424 = "() != null;";
  protected final String TEXT_1425 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1426 = " & Data";
  protected final String TEXT_1427 = ".";
  protected final String TEXT_1428 = "_EFLAG) != 0) != Data";
  protected final String TEXT_1429 = ".";
  protected final String TEXT_1430 = ";";
  protected final String TEXT_1431 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1432 = " & Data";
  protected final String TEXT_1433 = ".";
  protected final String TEXT_1434 = "_EFLAG) != Data";
  protected final String TEXT_1435 = ".";
  protected final String TEXT_1436 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1437 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1438 = "() != Data";
  protected final String TEXT_1439 = ".";
  protected final String TEXT_1440 = ";";
  protected final String TEXT_1441 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1442 = ", ";
  protected final String TEXT_1443 = ") != ";
  protected final String TEXT_1444 = ";";
  protected final String TEXT_1445 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1446 = "() != Data";
  protected final String TEXT_1447 = ".";
  protected final String TEXT_1448 = ";";
  protected final String TEXT_1449 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1450 = ".";
  protected final String TEXT_1451 = " == null ? ";
  protected final String TEXT_1452 = "() != null : !Data";
  protected final String TEXT_1453 = ".";
  protected final String TEXT_1454 = ".equals(";
  protected final String TEXT_1455 = "());";
  protected final String TEXT_1456 = NL + "\t\t\t\t";
  protected final String TEXT_1457 = " ";
  protected final String TEXT_1458 = " = (";
  protected final String TEXT_1459 = ")eVirtualGet(";
  protected final String TEXT_1460 = ", ";
  protected final String TEXT_1461 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1462 = " == null ? ";
  protected final String TEXT_1463 = "() != null : !";
  protected final String TEXT_1464 = ".equals(";
  protected final String TEXT_1465 = "());";
  protected final String TEXT_1466 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1467 = ".";
  protected final String TEXT_1468 = " == null ? ";
  protected final String TEXT_1469 = "() != null : !Data";
  protected final String TEXT_1470 = ".";
  protected final String TEXT_1471 = ".equals(";
  protected final String TEXT_1472 = "());";
  protected final String TEXT_1473 = NL + "\t\t}";
  protected final String TEXT_1474 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1475 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1476 = NL + "\t}" + NL + NL;
  protected final String TEXT_1477 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY19" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1478 = NL + "\t@Override";
  protected final String TEXT_1479 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1480 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1481 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1482 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1483 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1484 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1485 = ": return ";
  protected final String TEXT_1486 = ";";
  protected final String TEXT_1487 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1488 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1489 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY20" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1490 = NL + "\t@Override";
  protected final String TEXT_1491 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1492 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1493 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1494 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1495 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1496 = ": return ";
  protected final String TEXT_1497 = ";";
  protected final String TEXT_1498 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1499 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1500 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1501 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1502 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1503 = ": return ";
  protected final String TEXT_1504 = ";";
  protected final String TEXT_1505 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1506 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1507 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY21" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1508 = NL + "\t@Override";
  protected final String TEXT_1509 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1510 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1511 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1512 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1513 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1514 = ": return ";
  protected final String TEXT_1515 = ";";
  protected final String TEXT_1516 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1517 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1518 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1519 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1520 = ": return ";
  protected final String TEXT_1521 = ";";
  protected final String TEXT_1522 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1523 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1524 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1525 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1526 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1527 = ": return ";
  protected final String TEXT_1528 = ";";
  protected final String TEXT_1529 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1530 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1531 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY22" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1532 = NL + "\t@Override";
  protected final String TEXT_1533 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1534 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY23" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1535 = NL + "\t@Override";
  protected final String TEXT_1536 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1537 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1538 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY24" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1539 = NL + "\t@Override";
  protected final String TEXT_1540 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1541 = NL + "\t\t\tcase ";
  protected final String TEXT_1542 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1543 = ";";
  protected final String TEXT_1544 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY25" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1545 = NL + "\t@Override";
  protected final String TEXT_1546 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1547 = NL + "\t\t\tcase ";
  protected final String TEXT_1548 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1549 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1550 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1551 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY26" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1552 = NL + "\t@Override";
  protected final String TEXT_1553 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1554 = "\"unchecked\"";
  protected final String TEXT_1555 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1556 = ")";
  protected final String TEXT_1557 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1558 = " arguments) throws ";
  protected final String TEXT_1559 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1560 = ")" + NL + "\t\t{";
  protected final String TEXT_1561 = NL + "\t\t\tcase ";
  protected final String TEXT_1562 = ":";
  protected final String TEXT_1563 = NL + "\t\t\t\t";
  protected final String TEXT_1564 = "(";
  protected final String TEXT_1565 = "(";
  protected final String TEXT_1566 = "(";
  protected final String TEXT_1567 = ")";
  protected final String TEXT_1568 = "arguments.get(";
  protected final String TEXT_1569 = ")";
  protected final String TEXT_1570 = ").";
  protected final String TEXT_1571 = "()";
  protected final String TEXT_1572 = ", ";
  protected final String TEXT_1573 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1574 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1575 = "new ";
  protected final String TEXT_1576 = "(";
  protected final String TEXT_1577 = "(";
  protected final String TEXT_1578 = "(";
  protected final String TEXT_1579 = "(";
  protected final String TEXT_1580 = ")";
  protected final String TEXT_1581 = "arguments.get(";
  protected final String TEXT_1582 = ")";
  protected final String TEXT_1583 = ").";
  protected final String TEXT_1584 = "()";
  protected final String TEXT_1585 = ", ";
  protected final String TEXT_1586 = ")";
  protected final String TEXT_1587 = ")";
  protected final String TEXT_1588 = ";";
  protected final String TEXT_1589 = NL + "\t\t}";
  protected final String TEXT_1590 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1591 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1592 = NL + "\t}" + NL;
  protected final String TEXT_1593 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY27" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1594 = NL + "\t@Override";
  protected final String TEXT_1595 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());" + NL + "\t\tif (data != null) result.append(((Data";
  protected final String TEXT_1596 = ")data).toString());" + NL + "\t\t" + NL + "\t\treturn result.toString();" + NL + "\t\t}";
  protected final String TEXT_1597 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY28" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1598 = NL + "\t@";
  protected final String TEXT_1599 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY29" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1600 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY30" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY31" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1601 = " getKey()" + NL + "\t{";
  protected final String TEXT_1602 = NL + "\t\treturn new ";
  protected final String TEXT_1603 = "(getTypedKey());";
  protected final String TEXT_1604 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1605 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY32" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1606 = " key)" + NL + "\t{";
  protected final String TEXT_1607 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1608 = "(";
  protected final String TEXT_1609 = ")";
  protected final String TEXT_1610 = "key);";
  protected final String TEXT_1611 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1612 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1613 = ")key).";
  protected final String TEXT_1614 = "());";
  protected final String TEXT_1615 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1616 = ")key);";
  protected final String TEXT_1617 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY33" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1618 = " getValue()" + NL + "\t{";
  protected final String TEXT_1619 = NL + "\t\treturn new ";
  protected final String TEXT_1620 = "(getTypedValue());";
  protected final String TEXT_1621 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1622 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY34" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1623 = " setValue(";
  protected final String TEXT_1624 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1625 = " oldValue = getValue();";
  protected final String TEXT_1626 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1627 = "(";
  protected final String TEXT_1628 = ")";
  protected final String TEXT_1629 = "value);";
  protected final String TEXT_1630 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1631 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1632 = ")value).";
  protected final String TEXT_1633 = "());";
  protected final String TEXT_1634 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1635 = ")value);";
  protected final String TEXT_1636 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY35" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1637 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1638 = NL + "\tpublic ";
  protected final String TEXT_1639 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1640 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1641 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1642 = NL + NL + NL + NL + "// data Class generation ";
  protected final String TEXT_1643 = NL + "protected static  class Data";
  protected final String TEXT_1644 = NL + NL + "{" + NL;
  protected final String TEXT_1645 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_1646 = " copyright = ";
  protected final String TEXT_1647 = ";";
  protected final String TEXT_1648 = NL;
  protected final String TEXT_1649 = NL;
  protected final String TEXT_1650 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_1651 = "() <em>";
  protected final String TEXT_1652 = "</em>}' ";
  protected final String TEXT_1653 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1654 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1655 = NL + "\t@";
  protected final String TEXT_1656 = NL + "\tprotected ";
  protected final String TEXT_1657 = ".Internal.SettingDelegate ";
  protected final String TEXT_1658 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_1659 = ".Internal)";
  protected final String TEXT_1660 = ").getSettingDelegate();" + NL;
  protected final String TEXT_1661 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1662 = "() <em>";
  protected final String TEXT_1663 = "</em>}' ";
  protected final String TEXT_1664 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1665 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1666 = NL + "\t@";
  protected final String TEXT_1667 = NL + "\tprotected ";
  protected final String TEXT_1668 = " ";
  protected final String TEXT_1669 = ";" + NL;
  protected final String TEXT_1670 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_1671 = "() <em>";
  protected final String TEXT_1672 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1673 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1674 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1675 = NL + "\tprotected static final ";
  protected final String TEXT_1676 = "[] ";
  protected final String TEXT_1677 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_1678 = " [0]";
  protected final String TEXT_1679 = ";" + NL;
  protected final String TEXT_1680 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_1681 = "() <em>";
  protected final String TEXT_1682 = "</em>}' ";
  protected final String TEXT_1683 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1684 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1685 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1686 = NL + "\tprotected static final ";
  protected final String TEXT_1687 = " ";
  protected final String TEXT_1688 = "; // TODO The default value literal \"";
  protected final String TEXT_1689 = "\" is not valid.";
  protected final String TEXT_1690 = " = ";
  protected final String TEXT_1691 = ";";
  protected final String TEXT_1692 = NL;
  protected final String TEXT_1693 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1694 = NL + "\t@";
  protected final String TEXT_1695 = NL + "\tprotected int ";
  protected final String TEXT_1696 = " = 0;" + NL;
  protected final String TEXT_1697 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_1698 = "() <em>";
  protected final String TEXT_1699 = "</em>}' ";
  protected final String TEXT_1700 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1701 = "_EFLAG_OFFSET = ";
  protected final String TEXT_1702 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_1703 = "() <em>";
  protected final String TEXT_1704 = "</em>}' ";
  protected final String TEXT_1705 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1706 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_1707 = ".ordinal()";
  protected final String TEXT_1708 = ".VALUES.indexOf(";
  protected final String TEXT_1709 = ")";
  protected final String TEXT_1710 = " << ";
  protected final String TEXT_1711 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_1712 = " ";
  protected final String TEXT_1713 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_1714 = "[] ";
  protected final String TEXT_1715 = "_EFLAG_VALUES = ";
  protected final String TEXT_1716 = ".values()";
  protected final String TEXT_1717 = "(";
  protected final String TEXT_1718 = "[])";
  protected final String TEXT_1719 = ".VALUES.toArray(new ";
  protected final String TEXT_1720 = "[";
  protected final String TEXT_1721 = ".VALUES.size()])";
  protected final String TEXT_1722 = ";" + NL;
  protected final String TEXT_1723 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_1724 = " representing the value of the '{@link #";
  protected final String TEXT_1725 = "() <em>";
  protected final String TEXT_1726 = "</em>}' ";
  protected final String TEXT_1727 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1728 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1729 = "_EFLAG = ";
  protected final String TEXT_1730 = " << ";
  protected final String TEXT_1731 = "_EFLAG_OFFSET";
  protected final String TEXT_1732 = ";" + NL;
  protected final String TEXT_1733 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1734 = "() <em>";
  protected final String TEXT_1735 = "</em>}' ";
  protected final String TEXT_1736 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1737 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1738 = NL + "\t@";
  protected final String TEXT_1739 = NL + "\tprotected ";
  protected final String TEXT_1740 = " ";
  protected final String TEXT_1741 = " = ";
  protected final String TEXT_1742 = ";" + NL;
  protected final String TEXT_1743 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1744 = NL + "\t@";
  protected final String TEXT_1745 = NL + "\tprotected int ";
  protected final String TEXT_1746 = " = 0;" + NL;
  protected final String TEXT_1747 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_1748 = " ";
  protected final String TEXT_1749 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1750 = "_ESETFLAG = 1 << ";
  protected final String TEXT_1751 = ";" + NL;
  protected final String TEXT_1752 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_1753 = " ";
  protected final String TEXT_1754 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1755 = NL + "\t@";
  protected final String TEXT_1756 = NL + "\tprotected boolean ";
  protected final String TEXT_1757 = "ESet;" + NL;
  protected final String TEXT_1758 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_1759 = ".getOperationID(";
  protected final String TEXT_1760 = ") - ";
  protected final String TEXT_1761 = ";" + NL;
  protected final String TEXT_1762 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1763 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Data";
  protected final String TEXT_1764 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_1765 = NL + "\t\t";
  protected final String TEXT_1766 = " |= ";
  protected final String TEXT_1767 = "_EFLAG";
  protected final String TEXT_1768 = "_DEFAULT";
  protected final String TEXT_1769 = ";";
  protected final String TEXT_1770 = NL + "\t}" + NL + "\t" + NL + "\t\t";
  protected final String TEXT_1771 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1772 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param {@link ";
  protected final String TEXT_1773 = " }" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Data";
  protected final String TEXT_1774 = "(Data";
  protected final String TEXT_1775 = " data)" + NL + "\t{" + NL + "\t\tsuper();\t\t" + NL + "\t\t";
  protected final String TEXT_1776 = NL + "\t\t";
  protected final String TEXT_1777 = " = data.";
  protected final String TEXT_1778 = ";" + NL + "\t\t\t\t";
  protected final String TEXT_1779 = NL + "\t\t\t\t";
  protected final String TEXT_1780 = NL + "\t\t";
  protected final String TEXT_1781 = " |= ";
  protected final String TEXT_1782 = "_EFLAG";
  protected final String TEXT_1783 = "_DEFAULT";
  protected final String TEXT_1784 = ";";
  protected final String TEXT_1785 = NL + "\t}" + NL + "\t";
  protected final String TEXT_1786 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString(){\t" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1787 = "\t\t";
  protected final String TEXT_1788 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1789 = ": \");";
  protected final String TEXT_1790 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1791 = ": \");";
  protected final String TEXT_1792 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1793 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1794 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1795 = NL + "\t\tif (";
  protected final String TEXT_1796 = "(";
  protected final String TEXT_1797 = " & ";
  protected final String TEXT_1798 = "_ESETFLAG) != 0";
  protected final String TEXT_1799 = "ESet";
  protected final String TEXT_1800 = ") result.append((";
  protected final String TEXT_1801 = " & ";
  protected final String TEXT_1802 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1803 = NL + "\t\tif (";
  protected final String TEXT_1804 = "(";
  protected final String TEXT_1805 = " & ";
  protected final String TEXT_1806 = "_ESETFLAG) != 0";
  protected final String TEXT_1807 = "ESet";
  protected final String TEXT_1808 = ") result.append(";
  protected final String TEXT_1809 = "_EFLAG_VALUES[(";
  protected final String TEXT_1810 = " & ";
  protected final String TEXT_1811 = "_EFLAG) >>> ";
  protected final String TEXT_1812 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1813 = NL + "\t\tif (";
  protected final String TEXT_1814 = "(";
  protected final String TEXT_1815 = " & ";
  protected final String TEXT_1816 = "_ESETFLAG) != 0";
  protected final String TEXT_1817 = "ESet";
  protected final String TEXT_1818 = ") result.append(";
  protected final String TEXT_1819 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1820 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1821 = ", ";
  protected final String TEXT_1822 = "));";
  protected final String TEXT_1823 = NL + "\t\tresult.append((";
  protected final String TEXT_1824 = " & ";
  protected final String TEXT_1825 = "_EFLAG) != 0);";
  protected final String TEXT_1826 = NL + "\t\tresult.append(";
  protected final String TEXT_1827 = "_EFLAG_VALUES[(";
  protected final String TEXT_1828 = " & ";
  protected final String TEXT_1829 = "_EFLAG) >>> ";
  protected final String TEXT_1830 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1831 = NL + "\t\tresult.append(";
  protected final String TEXT_1832 = ");";
  protected final String TEXT_1833 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL + "\t\t";
  protected final String TEXT_1834 = NL + "}";
  protected final String TEXT_1835 = NL + "} //";
  protected final String TEXT_1836 = NL;

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
 genClass.addClassPsuedoImports();
 genModel.addImport("fr.inria.atlanmod.neo4emf.INeo4emfResource");
 genModel.addImport("fr.inria.atlanmod.neo4emf.INeo4emfNotification");}
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
    stringBuffer.append(TEXT_48);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_52);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_53);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_54);
    if (isGWT) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_56);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_57);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_58);
    if (isGWT) {
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_60);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_61);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_62);
    if (isGWT) {
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(TEXT_66);
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_71);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_76);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_80);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getRootExtendsClass());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getClassExtends().substring(9));
    stringBuffer.append(TEXT_83);
    if (genModel.getRootExtendsClass().contains(genClass.getClassExtends().substring(9))){
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_86);
    }
    stringBuffer.append(TEXT_87);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_88);
    } else {
    stringBuffer.append(TEXT_89);
    }
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_91);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_94);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_95);
    }
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_101);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    stringBuffer.append(TEXT_102);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_103);
    }
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_105);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_106);
    if (!isImplementation) {
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_109);
    } else {
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_112);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_116);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_118);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_119);
    } else {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_122);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_124);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_128);
    }
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_130);
    }
    stringBuffer.append(TEXT_131);
    if (!isImplementation) {
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_134);
    } else {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_137);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_140);
    }
    stringBuffer.append(TEXT_141);
    if (!isImplementation) {
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_143);
    } else {
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_145);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_147);
    } else {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_150);
    }
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_152);
    if (!isImplementation) {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_156);
    } else {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_164);
    }
    stringBuffer.append(TEXT_165);
    if (!isImplementation) {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_168);
    } else {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_172);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_175);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_176);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_177);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_178);
    } else {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_180);
    }
    stringBuffer.append(TEXT_181);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_182);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_183);
    } else {
    stringBuffer.append(TEXT_184);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_185);
    }
    stringBuffer.append(TEXT_186);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_187);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_189);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_191);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_193);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_197);
    }
    }
    stringBuffer.append(TEXT_198);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_201);
    }
    stringBuffer.append(TEXT_202);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_204);
    }
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_207);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_210);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_212);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_215);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_218);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_219);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_220);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_221);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_222);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_223);
    }}
    stringBuffer.append(TEXT_224);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_225);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_228);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_229);
    }
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_232);
    }
    stringBuffer.append(TEXT_233);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_234);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_235);
    }
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_240);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_242);
    }
    stringBuffer.append(TEXT_243);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_244);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_245);
    }
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_248);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_250);
    }
    stringBuffer.append(TEXT_251);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_252);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_253);
    }
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_256);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_258);
    }
    stringBuffer.append(TEXT_259);
    } 
	else if (!genFeature.isVolatile()) {
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_264);
    }
    stringBuffer.append(TEXT_265);
     if (!genFeature.isContainer()){
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_268);
    if (! genFeature.isReferenceType()){ 
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_271);
     }}
    stringBuffer.append(TEXT_272);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_275);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_279);
    } else {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_284);
    }
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_287);
    } 	  	  
	  else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getImportedType(genClass));
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
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_301);
    }
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_305);
    }
    stringBuffer.append(TEXT_306);
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_310);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_314);
    } else {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    }
    } else {
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_326);
    }
    }
    } 	
	else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_336);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_340);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_343);
    } else {
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_345);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_348);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_352);
    } else {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_355);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_356);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_357);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_359);
    }
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_361);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_363);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_365);
    }
    stringBuffer.append(TEXT_366);
    } else {
    stringBuffer.append(TEXT_367);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_368);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_370);
    }
    stringBuffer.append(TEXT_371);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_373);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_375);
    }
    stringBuffer.append(TEXT_376);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_381);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_382);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_383);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_384);
    } else {
    stringBuffer.append(TEXT_385);
    }
    stringBuffer.append(TEXT_386);
    }
    stringBuffer.append(TEXT_387);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_388);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_389);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_392);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_396);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_397);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_398);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_399);
    }
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_402);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_404);
    }
    stringBuffer.append(TEXT_405);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_408);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_411);
    } else {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_414);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_417);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_419);
    } else {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_421);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_423);
    }
    } else {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_426);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_427);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_428);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_434);
     if (! genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && ! genFeature.hasSettingDelegate() && ! genFeature.isContainer() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_436);
    }
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_440);
    stringBuffer.append(TEXT_441);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_445);
    stringBuffer.append(TEXT_446);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_450);
    } else {
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_456);
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
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_540);
    if (CodegenUtil.getDataClassExtends(genClass) != ""){
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
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
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_602);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_611);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_616);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_620);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_621);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_622);
    }
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_626);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_627);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_630);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_634);
    }
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_637);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_640);
    }
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_642);
    }
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_649);
    }
    stringBuffer.append(TEXT_650);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_656);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_662);
    } else {
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_670);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_678);
    } else {
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_686);
    if (isJDK50) {
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_688);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_690);
    }
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_692);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_697);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_703);
    } else {
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_710);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    } else {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_720);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_724);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_726);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_730);
    }
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_733);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_736);
    }
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_738);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_742);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_746);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_748);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_749);
    } else {
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_751);
    }
    stringBuffer.append(TEXT_752);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_756);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_761);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_765);
    }
    }
    }
    } 
	else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_768);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_770);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_772);
    }
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_774);
    }
    stringBuffer.append(TEXT_775);
    } else {
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_777);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_779);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_781);
    }
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_783);
    }
    stringBuffer.append(TEXT_784);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_785);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_788);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_789);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_790);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_794);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_796);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_799);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_801);
    }
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_803);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_807);
    }
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_809);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_811);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_815);
    }
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_818);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_821);
    }
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_823);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_828);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_830);
    } else {
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_832);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_833);
    } else {
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_835);
    }
    stringBuffer.append(TEXT_836);
    }
    } else {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_839);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_840);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_845);
    stringBuffer.append(TEXT_846);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_848);
    }
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_850);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_853);
    }
    stringBuffer.append(TEXT_854);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_855);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_857);
    } else {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_859);
    }
    stringBuffer.append(TEXT_860);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_863);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_865);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_867);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
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
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_876);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_878);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_883);
    }
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_887);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_892);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_896);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_897);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_898);
    }
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_900);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_903);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_907);
    }
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_910);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_913);
    }
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_915);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_920);
    }
    stringBuffer.append(TEXT_921);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_925);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_926);
    } else {
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_933);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_936);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_940);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_942);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_946);
    } else {
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_949);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_951);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_954);
    } else {
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_956);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_960);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_962);
    } else {
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_964);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_965);
    } else {
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_967);
    }
    stringBuffer.append(TEXT_968);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_974);
    } else {
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_979);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_982);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_985);
    } else {
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_987);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_991);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_996);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_997);
    } else {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_999);
    }
    stringBuffer.append(TEXT_1000);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1005);
    } else {
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1009);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1013);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1014);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1022);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1024);
    }
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1026);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1029);
    }
    stringBuffer.append(TEXT_1030);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1031);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1033);
    } else {
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1035);
    }
    stringBuffer.append(TEXT_1036);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1039);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1041);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1043);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1048);
    }
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1053);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1055);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1058);
    } else {
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1060);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1065);
    } else {
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1069);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1073);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1074);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
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
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1084);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1094);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(TEXT_1096);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1097);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1103);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1106);
    }}
    stringBuffer.append(TEXT_1107);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1108);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1113);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1114);
    }
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1119);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1133);
    } else {
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1146);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1147);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1149);
    if (size > 0) {
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1153);
    } else {
    stringBuffer.append(TEXT_1154);
    }
    stringBuffer.append(TEXT_1155);
    } else {
    stringBuffer.append(TEXT_1156);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1157);
    }
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1160);
    if (size > 0) {
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1164);
    } else {
    stringBuffer.append(TEXT_1165);
    }
    stringBuffer.append(TEXT_1166);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1168);
    }
    stringBuffer.append(TEXT_1169);
    }
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1172);
    } else {
    stringBuffer.append(TEXT_1173);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1174);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1175);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1176);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1177);
    }
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1181);
    stringBuffer.append( genClass.getInterfaceName());
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1183);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1185);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1190);
    } else {
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1192);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1193);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1196);
    } else {
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1198);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1203);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1206);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1208);
    }
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1211);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1216);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1222);
    }
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1225);
    }
    }
    stringBuffer.append(TEXT_1226);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1227);
    } else {
    stringBuffer.append(TEXT_1228);
    }
    stringBuffer.append(TEXT_1229);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1230);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1231);
    }
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1236);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1238);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1242);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1243);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1244);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1245);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1247);
    } else {
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1250);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1252);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1254);
    } else {
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1256);
    }
    }
    stringBuffer.append(TEXT_1257);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1258);
    } else {
    stringBuffer.append(TEXT_1259);
    }
    stringBuffer.append(TEXT_1260);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1261);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1262);
    }
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1266);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1270);
    }
    stringBuffer.append(TEXT_1271);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1272);
    } else {
    stringBuffer.append(TEXT_1273);
    }
    stringBuffer.append(TEXT_1274);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1275);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1276);
    }
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1278);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1280);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1282);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1284);
    } else {
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1287);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1290);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1293);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1295);
    } else {
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1298);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1302);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1306);
    } else {
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1308);
    }
    }
    stringBuffer.append(TEXT_1309);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1310);
    } else {
    stringBuffer.append(TEXT_1311);
    }
    stringBuffer.append(TEXT_1312);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1313);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1314);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1315);
    }
    stringBuffer.append(TEXT_1316);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1317);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1319);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1323);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1326);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1331);
    } else {
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1334);
    }
    } else {
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1337);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1339);
    }
    stringBuffer.append(TEXT_1340);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1341);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1342);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1344);
    } else {
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1346);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1347);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1348);
    }
    stringBuffer.append(TEXT_1349);
    }
    stringBuffer.append(TEXT_1350);
    }
    stringBuffer.append(TEXT_1351);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1352);
    } else {
    stringBuffer.append(TEXT_1353);
    }
    stringBuffer.append(TEXT_1354);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1355);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1356);
    }
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1358);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1360);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1361);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1363);
    } else {
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1365);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1367);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1368);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1370);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1372);
    } else {
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1374);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1376);
    }
    stringBuffer.append(TEXT_1377);
    }
    stringBuffer.append(TEXT_1378);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1379);
    } else {
    stringBuffer.append(TEXT_1380);
    }
    stringBuffer.append(TEXT_1381);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1382);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1383);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1384);
    }
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1386);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1388);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1390);
    } else {
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1392);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1393);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1395);
    } else {
    stringBuffer.append(TEXT_1396);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1397);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1398);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1400);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1401);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1402);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1404);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1408);
    } else {
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1410);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1412);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1414);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1416);
    } else {
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1418);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1420);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1422);
    } else {
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1424);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1425);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1430);
    } else {
    stringBuffer.append(TEXT_1431);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1432);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1433);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1436);
    }
    } else {
    stringBuffer.append(TEXT_1437);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1438);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1439);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1440);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1441);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1442);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1443);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1444);
    } else {
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1448);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1454);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1455);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1458);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1460);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1461);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1462);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1463);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1464);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1465);
    } else {
    stringBuffer.append(TEXT_1466);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1469);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1472);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1473);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1474);
    } else {
    stringBuffer.append(TEXT_1475);
    }
    stringBuffer.append(TEXT_1476);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1477);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1478);
    }
    stringBuffer.append(TEXT_1479);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1480);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1483);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1486);
    }
    stringBuffer.append(TEXT_1487);
    }
    stringBuffer.append(TEXT_1488);
    }
    stringBuffer.append(TEXT_1489);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1490);
    }
    stringBuffer.append(TEXT_1491);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1492);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1494);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1495);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1497);
    }
    stringBuffer.append(TEXT_1498);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1499);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1501);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1504);
    }
    stringBuffer.append(TEXT_1505);
    }
    stringBuffer.append(TEXT_1506);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1507);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1508);
    }
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1510);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1512);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1514);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1515);
    }
    }
    stringBuffer.append(TEXT_1516);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1517);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1518);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1520);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1521);
    }
    stringBuffer.append(TEXT_1522);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1523);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1524);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1525);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1526);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1528);
    }
    stringBuffer.append(TEXT_1529);
    }
    stringBuffer.append(TEXT_1530);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1531);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1532);
    }
    stringBuffer.append(TEXT_1533);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1534);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1535);
    }
    stringBuffer.append(TEXT_1536);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1537);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1538);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1539);
    }
    stringBuffer.append(TEXT_1540);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1541);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1542);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1543);
    }
    stringBuffer.append(TEXT_1544);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1545);
    }
    stringBuffer.append(TEXT_1546);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1547);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1548);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1549);
    }
    stringBuffer.append(TEXT_1550);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1551);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1552);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1553);
    if (!isRaw) {
    stringBuffer.append(TEXT_1554);
    } else {
    stringBuffer.append(TEXT_1555);
    }
    stringBuffer.append(TEXT_1556);
    }
    }
    stringBuffer.append(TEXT_1557);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1558);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1559);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1560);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1562);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1563);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1564);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1565);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1566);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1567);
    }
    stringBuffer.append(TEXT_1568);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1569);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1570);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1571);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1572);
    }
    }
    stringBuffer.append(TEXT_1573);
    } else {
    stringBuffer.append(TEXT_1574);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1575);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1576);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1577);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1578);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1579);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1580);
    }
    stringBuffer.append(TEXT_1581);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1582);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1583);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1584);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1585);
    }
    }
    stringBuffer.append(TEXT_1586);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1587);
    }
    stringBuffer.append(TEXT_1588);
    }
    }
    stringBuffer.append(TEXT_1589);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1590);
    } else {
    stringBuffer.append(TEXT_1591);
    }
    stringBuffer.append(TEXT_1592);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1593);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1594);
    }
    stringBuffer.append(TEXT_1595);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1596);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1597);
    if (isGWT) {
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1599);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1600);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1601);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1602);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1603);
    } else {
    stringBuffer.append(TEXT_1604);
    }
    stringBuffer.append(TEXT_1605);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1606);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1607);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1608);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1609);
    }
    stringBuffer.append(TEXT_1610);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1611);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1612);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1614);
    } else {
    stringBuffer.append(TEXT_1615);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1616);
    }
    stringBuffer.append(TEXT_1617);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1618);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1619);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1620);
    } else {
    stringBuffer.append(TEXT_1621);
    }
    stringBuffer.append(TEXT_1622);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1623);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1624);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1625);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1626);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1627);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1628);
    }
    stringBuffer.append(TEXT_1629);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1630);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1631);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1632);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1633);
    } else {
    stringBuffer.append(TEXT_1634);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1635);
    }
    stringBuffer.append(TEXT_1636);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1637);
    }
    stringBuffer.append(TEXT_1638);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1640);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1641);
    }
    stringBuffer.append(TEXT_1642);
    if (isImplementation) {
    stringBuffer.append(TEXT_1643);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(CodegenUtil.getDataClassExtends(genClass));
    stringBuffer.append(TEXT_1644);
     if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_1645);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1646);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_1647);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1648);
    }
    stringBuffer.append(TEXT_1649);
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1651);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1652);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1653);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1654);
    if (isGWT) {
    stringBuffer.append(TEXT_1655);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1656);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1657);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1658);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1659);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1660);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1661);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1662);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1663);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1664);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1665);
    if (isGWT) {
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1667);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1669);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_1670);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1671);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1673);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_1674);
    }
    stringBuffer.append(TEXT_1675);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_1676);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1677);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_1678);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_1679);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_1680);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1682);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1683);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1684);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_1685);
    }
    stringBuffer.append(TEXT_1686);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1687);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_1689);
    } else {
    stringBuffer.append(TEXT_1690);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_1691);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_1692);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1693);
    if (isGWT) {
    stringBuffer.append(TEXT_1694);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1695);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1696);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_1697);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1699);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1700);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1701);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_1702);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1703);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1704);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1706);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1707);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1708);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1709);
    }
    stringBuffer.append(TEXT_1710);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1711);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1714);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1715);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1716);
    } else {
    stringBuffer.append(TEXT_1717);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1718);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1720);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1721);
    }
    stringBuffer.append(TEXT_1722);
    }
    stringBuffer.append(TEXT_1723);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1725);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1726);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1727);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1728);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_1730);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1731);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_1732);
    } else {
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1734);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1735);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1736);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1737);
    if (isGWT) {
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1739);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1740);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1741);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1742);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1743);
    if (isGWT) {
    stringBuffer.append(TEXT_1744);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1745);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1746);
    }
    stringBuffer.append(TEXT_1747);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1749);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1750);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_1751);
    } else {
    stringBuffer.append(TEXT_1752);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1753);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1754);
    if (isGWT) {
    stringBuffer.append(TEXT_1755);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1756);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1757);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1758);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1759);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1760);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_1761);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_1762);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1763);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1764);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_1765);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1766);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1767);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1768);
    }
    stringBuffer.append(TEXT_1769);
    }
    stringBuffer.append(TEXT_1770);
    if ( CodegenUtil.getDataClassExtends (genClass) !=""){
    stringBuffer.append(TEXT_1771);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1772);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
    stringBuffer.append(TEXT_1773);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1774);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
    stringBuffer.append(TEXT_1775);
     for (GenFeature genFeat : genClass.getClassExtendsGenClass().getDeclaredFieldGenFeatures()){
    stringBuffer.append(TEXT_1776);
    stringBuffer.append(genFeat.getSafeName());
    stringBuffer.append(TEXT_1777);
    stringBuffer.append(genFeat.getSafeName());
    stringBuffer.append(TEXT_1778);
    }
    stringBuffer.append(TEXT_1779);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_1780);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1781);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1782);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1783);
    }
    stringBuffer.append(TEXT_1784);
    }
    stringBuffer.append(TEXT_1785);
    }
    stringBuffer.append(TEXT_1786);
    { boolean first = true;
    stringBuffer.append(TEXT_1787);
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1788);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1789);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1790);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1791);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1792);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1793);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1794);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1795);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1796);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1797);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1798);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1799);
    }
    stringBuffer.append(TEXT_1800);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1801);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1802);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1803);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1804);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1805);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1806);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1807);
    }
    stringBuffer.append(TEXT_1808);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1809);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1810);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1811);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1812);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1813);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1814);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1815);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1816);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1817);
    }
    stringBuffer.append(TEXT_1818);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1819);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1820);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1821);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1822);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1823);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1824);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1825);
    } else {
    stringBuffer.append(TEXT_1826);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1827);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1828);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1829);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1830);
    }
    } else {
    stringBuffer.append(TEXT_1831);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1832);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1833);
    }
    stringBuffer.append(TEXT_1834);
    } 
    stringBuffer.append(TEXT_1835);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1836);
    return stringBuffer.toString();
  }
}
