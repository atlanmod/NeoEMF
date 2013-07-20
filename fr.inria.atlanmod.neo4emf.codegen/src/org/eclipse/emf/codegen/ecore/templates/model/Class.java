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
  protected final String TEXT_265 = NL + "\t\tif (getNodeId()<0 && data == null ) " + NL + "\t\t\treturn null;" + NL + "\t\tif ( data == null || !(data instanceof Data";
  protected final String TEXT_266 = ")){" + NL + "\t\t\tdata = new Data";
  protected final String TEXT_267 = "();" + NL + "\t\t\t((INeo4emfResource) this.eResource()).fetchAttributes(this);" + NL + "\t\t\t\t}" + NL + "\t  ";
  protected final String TEXT_268 = " " + NL + "\t\tif (((Data";
  protected final String TEXT_269 = ")data).";
  protected final String TEXT_270 = " == null)\t" + NL + "\t\t{";
  protected final String TEXT_271 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_272 = ", ";
  protected final String TEXT_273 = " = new ";
  protected final String TEXT_274 = ");";
  protected final String TEXT_275 = NL + "\t\t\t((Data";
  protected final String TEXT_276 = ")data).";
  protected final String TEXT_277 = " = new ";
  protected final String TEXT_278 = ";" + NL + "\t\t\tif (getNodeId()>-1) " + NL + "\t\t\t((INeo4emfResource) this.eResource()).getOnDemand(this, ";
  protected final String TEXT_279 = ");\t\t\t";
  protected final String TEXT_280 = NL + "\t\t}" + NL + "\t\treturn ((Data";
  protected final String TEXT_281 = ")data).";
  protected final String TEXT_282 = ";";
  protected final String TEXT_283 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_284 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_285 = ")eContainer();";
  protected final String TEXT_286 = NL + "\t\t";
  protected final String TEXT_287 = " ";
  protected final String TEXT_288 = " = (";
  protected final String TEXT_289 = ")eVirtualGet(";
  protected final String TEXT_290 = ", ";
  protected final String TEXT_291 = ");";
  protected final String TEXT_292 = NL + "\t\tif (((Data";
  protected final String TEXT_293 = ")data).";
  protected final String TEXT_294 = " == null && getNodeId()>-1)" + NL + "\t\t{" + NL + "\t\t\t((INeo4emfResource) this.eResource()).getOnDemand(this, ";
  protected final String TEXT_295 = ");" + NL + "\t\t}";
  protected final String TEXT_296 = "\t\t";
  protected final String TEXT_297 = NL + "\t\treturn (";
  protected final String TEXT_298 = ")eVirtualGet(";
  protected final String TEXT_299 = ", ";
  protected final String TEXT_300 = ");";
  protected final String TEXT_301 = NL + "\t\treturn (";
  protected final String TEXT_302 = " & Data";
  protected final String TEXT_303 = ".";
  protected final String TEXT_304 = "_EFLAG) != 0;";
  protected final String TEXT_305 = NL + "\t\treturn Data";
  protected final String TEXT_306 = ".";
  protected final String TEXT_307 = "_EFLAG_VALUES[(";
  protected final String TEXT_308 = " & Data";
  protected final String TEXT_309 = ".";
  protected final String TEXT_310 = "_EFLAG) >>> Data";
  protected final String TEXT_311 = ".";
  protected final String TEXT_312 = "_EFLAG_OFFSET];";
  protected final String TEXT_313 = NL + "\t\tif ( getNodeId()>-1 ) " + NL + "\t\t\teNotify(new ENotificationImpl(this, INeo4emfNotification.GET, ";
  protected final String TEXT_314 = ", null, null));" + NL + "\t\treturn ((Data";
  protected final String TEXT_315 = ")data).";
  protected final String TEXT_316 = ";";
  protected final String TEXT_317 = NL + "\t\t";
  protected final String TEXT_318 = " ";
  protected final String TEXT_319 = " = basicGet";
  protected final String TEXT_320 = "();" + NL + "\t\treturn ";
  protected final String TEXT_321 = " != null && ";
  protected final String TEXT_322 = ".eIsProxy() ? ";
  protected final String TEXT_323 = "eResolveProxy((";
  protected final String TEXT_324 = ")";
  protected final String TEXT_325 = ") : ";
  protected final String TEXT_326 = ";";
  protected final String TEXT_327 = NL + "\t\treturn new ";
  protected final String TEXT_328 = "((";
  protected final String TEXT_329 = ".Internal)((";
  protected final String TEXT_330 = ".Internal.Wrapper)get";
  protected final String TEXT_331 = "()).featureMap().";
  protected final String TEXT_332 = "list(";
  protected final String TEXT_333 = "));";
  protected final String TEXT_334 = NL + "\t\treturn (";
  protected final String TEXT_335 = ")get";
  protected final String TEXT_336 = "().";
  protected final String TEXT_337 = "list(";
  protected final String TEXT_338 = ");";
  protected final String TEXT_339 = NL + "\t\treturn ((";
  protected final String TEXT_340 = ".Internal.Wrapper)get";
  protected final String TEXT_341 = "()).featureMap().list(";
  protected final String TEXT_342 = ");";
  protected final String TEXT_343 = NL + "\t\treturn get";
  protected final String TEXT_344 = "().list(";
  protected final String TEXT_345 = ");";
  protected final String TEXT_346 = NL + "\t\treturn ";
  protected final String TEXT_347 = "(";
  protected final String TEXT_348 = "(";
  protected final String TEXT_349 = ")";
  protected final String TEXT_350 = "((";
  protected final String TEXT_351 = ".Internal.Wrapper)get";
  protected final String TEXT_352 = "()).featureMap().get(";
  protected final String TEXT_353 = ", true)";
  protected final String TEXT_354 = ").";
  protected final String TEXT_355 = "()";
  protected final String TEXT_356 = ";";
  protected final String TEXT_357 = NL + "\t\treturn ";
  protected final String TEXT_358 = "(";
  protected final String TEXT_359 = "(";
  protected final String TEXT_360 = ")";
  protected final String TEXT_361 = "get";
  protected final String TEXT_362 = "().get(";
  protected final String TEXT_363 = ", true)";
  protected final String TEXT_364 = ").";
  protected final String TEXT_365 = "()";
  protected final String TEXT_366 = ";";
  protected final String TEXT_367 = NL + "\t\t";
  protected final String TEXT_368 = NL + "\t\t";
  protected final String TEXT_369 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_370 = "' ";
  protected final String TEXT_371 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_372 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_373 = "EcoreEMap";
  protected final String TEXT_374 = "BasicFeatureMap";
  protected final String TEXT_375 = "EcoreEList";
  protected final String TEXT_376 = " should be used.";
  protected final String TEXT_377 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_378 = "\t" + NL + "\t}";
  protected final String TEXT_379 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_380 = NL + "\tpublic ";
  protected final String TEXT_381 = " basicGet";
  protected final String TEXT_382 = "()" + NL + "\t{";
  protected final String TEXT_383 = NL + "\t\treturn (";
  protected final String TEXT_384 = ")eDynamicGet(";
  protected final String TEXT_385 = ", ";
  protected final String TEXT_386 = ", false, ";
  protected final String TEXT_387 = ");";
  protected final String TEXT_388 = NL + "\t\treturn ";
  protected final String TEXT_389 = "(";
  protected final String TEXT_390 = "(";
  protected final String TEXT_391 = ")";
  protected final String TEXT_392 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_393 = ").";
  protected final String TEXT_394 = "()";
  protected final String TEXT_395 = ";";
  protected final String TEXT_396 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_397 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_398 = ")eInternalContainer();";
  protected final String TEXT_399 = NL + "\t\treturn (";
  protected final String TEXT_400 = ")eVirtualGet(";
  protected final String TEXT_401 = ");";
  protected final String TEXT_402 = NL + "\t\treturn data != null ? ((Data";
  protected final String TEXT_403 = ")data).";
  protected final String TEXT_404 = " : null;";
  protected final String TEXT_405 = NL + "\t\treturn (";
  protected final String TEXT_406 = ")((";
  protected final String TEXT_407 = ".Internal.Wrapper)get";
  protected final String TEXT_408 = "()).featureMap().get(";
  protected final String TEXT_409 = ", false);";
  protected final String TEXT_410 = NL + "\t\treturn (";
  protected final String TEXT_411 = ")get";
  protected final String TEXT_412 = "().get(";
  protected final String TEXT_413 = ", false);";
  protected final String TEXT_414 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_415 = "' ";
  protected final String TEXT_416 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_417 = NL + "\t}" + NL;
  protected final String TEXT_418 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_419 = NL + "\tpublic ";
  protected final String TEXT_420 = " basicSet";
  protected final String TEXT_421 = "(";
  protected final String TEXT_422 = " new";
  protected final String TEXT_423 = ", ";
  protected final String TEXT_424 = " msgs)" + NL + "\t{" + NL + "\t";
  protected final String TEXT_425 = NL + "\t\tif (data==null) data =  new Data";
  protected final String TEXT_426 = "();" + NL + "\t";
  protected final String TEXT_427 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_428 = ")new";
  protected final String TEXT_429 = ", ";
  protected final String TEXT_430 = ", msgs);";
  protected final String TEXT_431 = NL + "\t\treturn msgs;";
  protected final String TEXT_432 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_433 = ")new";
  protected final String TEXT_434 = ", ";
  protected final String TEXT_435 = ", msgs);";
  protected final String TEXT_436 = NL + "\t\treturn msgs;";
  protected final String TEXT_437 = NL + "\t\tObject old";
  protected final String TEXT_438 = " = eVirtualSet(";
  protected final String TEXT_439 = ", new";
  protected final String TEXT_440 = ");";
  protected final String TEXT_441 = NL + "\t\t";
  protected final String TEXT_442 = " old";
  protected final String TEXT_443 = " = ((Data";
  protected final String TEXT_444 = ")data).";
  protected final String TEXT_445 = ";" + NL + "\t\t((Data";
  protected final String TEXT_446 = ")data).";
  protected final String TEXT_447 = " = new";
  protected final String TEXT_448 = ";";
  protected final String TEXT_449 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_450 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_451 = NL + "\t\tboolean old";
  protected final String TEXT_452 = "ESet = (";
  protected final String TEXT_453 = " & ";
  protected final String TEXT_454 = "_ESETFLAG) != 0;";
  protected final String TEXT_455 = NL + "\t\t";
  protected final String TEXT_456 = " |= ";
  protected final String TEXT_457 = "_ESETFLAG;";
  protected final String TEXT_458 = NL + "\t\tboolean old";
  protected final String TEXT_459 = "ESet = ";
  protected final String TEXT_460 = "ESet;";
  protected final String TEXT_461 = NL + "\t\t";
  protected final String TEXT_462 = "ESet = true;";
  protected final String TEXT_463 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_464 = NL + "\t\t\t";
  protected final String TEXT_465 = " notification = new ";
  protected final String TEXT_466 = "(this, ";
  protected final String TEXT_467 = ".SET, ";
  protected final String TEXT_468 = ", ";
  protected final String TEXT_469 = "isSetChange ? null : old";
  protected final String TEXT_470 = "old";
  protected final String TEXT_471 = ", new";
  protected final String TEXT_472 = ", ";
  protected final String TEXT_473 = "isSetChange";
  protected final String TEXT_474 = "!old";
  protected final String TEXT_475 = "ESet";
  protected final String TEXT_476 = ");";
  protected final String TEXT_477 = NL + "\t\t\t";
  protected final String TEXT_478 = " notification = new ";
  protected final String TEXT_479 = "(this, ";
  protected final String TEXT_480 = ".SET, ";
  protected final String TEXT_481 = ", ";
  protected final String TEXT_482 = "old";
  protected final String TEXT_483 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_484 = "old";
  protected final String TEXT_485 = ", new";
  protected final String TEXT_486 = ");";
  protected final String TEXT_487 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_488 = NL + "\t\treturn msgs;";
  protected final String TEXT_489 = NL + "\t\treturn ((";
  protected final String TEXT_490 = ".Internal)((";
  protected final String TEXT_491 = ".Internal.Wrapper)get";
  protected final String TEXT_492 = "()).featureMap()).basicAdd(";
  protected final String TEXT_493 = ", new";
  protected final String TEXT_494 = ", msgs);";
  protected final String TEXT_495 = NL + "\t\treturn ((";
  protected final String TEXT_496 = ".Internal)get";
  protected final String TEXT_497 = "()).basicAdd(";
  protected final String TEXT_498 = ", new";
  protected final String TEXT_499 = ", msgs);";
  protected final String TEXT_500 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_501 = "' ";
  protected final String TEXT_502 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_503 = NL + "\t}" + NL;
  protected final String TEXT_504 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_505 = "#";
  protected final String TEXT_506 = " <em>";
  protected final String TEXT_507 = "</em>}' ";
  protected final String TEXT_508 = ".";
  protected final String TEXT_509 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY1" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_510 = "</em>' ";
  protected final String TEXT_511 = ".";
  protected final String TEXT_512 = NL + "\t * @see ";
  protected final String TEXT_513 = NL + "\t * @see #isSet";
  protected final String TEXT_514 = "()";
  protected final String TEXT_515 = NL + "\t * @see #unset";
  protected final String TEXT_516 = "()";
  protected final String TEXT_517 = NL + "\t * @see #";
  protected final String TEXT_518 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_519 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY2" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_520 = NL + "\tvoid set";
  protected final String TEXT_521 = "(";
  protected final String TEXT_522 = " value);" + NL;
  protected final String TEXT_523 = NL + "\tpublic void set";
  protected final String TEXT_524 = "_";
  protected final String TEXT_525 = "(";
  protected final String TEXT_526 = " ";
  protected final String TEXT_527 = ")" + NL + "\t{";
  protected final String TEXT_528 = NL + "\t";
  protected final String TEXT_529 = NL + "\t\tif (data==null) data =  new Data";
  protected final String TEXT_530 = "();" + NL + "\t";
  protected final String TEXT_531 = NL + "\t\teDynamicSet(";
  protected final String TEXT_532 = ", ";
  protected final String TEXT_533 = ", ";
  protected final String TEXT_534 = "new ";
  protected final String TEXT_535 = "(";
  protected final String TEXT_536 = "new";
  protected final String TEXT_537 = ")";
  protected final String TEXT_538 = ");";
  protected final String TEXT_539 = NL + "\t\teSet(";
  protected final String TEXT_540 = ", ";
  protected final String TEXT_541 = "new ";
  protected final String TEXT_542 = "(";
  protected final String TEXT_543 = "new";
  protected final String TEXT_544 = ")";
  protected final String TEXT_545 = ");";
  protected final String TEXT_546 = NL + "\t\t";
  protected final String TEXT_547 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_548 = "new ";
  protected final String TEXT_549 = "(";
  protected final String TEXT_550 = "new";
  protected final String TEXT_551 = ")";
  protected final String TEXT_552 = ");";
  protected final String TEXT_553 = NL + "\t\tif (new";
  protected final String TEXT_554 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_555 = " && new";
  protected final String TEXT_556 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_557 = ".isAncestor(this, ";
  protected final String TEXT_558 = "new";
  protected final String TEXT_559 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_560 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_561 = NL + "\t\t\t";
  protected final String TEXT_562 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_563 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_564 = ")new";
  protected final String TEXT_565 = ").eInverseAdd(this, ";
  protected final String TEXT_566 = ", ";
  protected final String TEXT_567 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_568 = "(";
  protected final String TEXT_569 = "new";
  protected final String TEXT_570 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_571 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_572 = "(this, ";
  protected final String TEXT_573 = ".SET, ";
  protected final String TEXT_574 = ", new";
  protected final String TEXT_575 = ", new";
  protected final String TEXT_576 = "));";
  protected final String TEXT_577 = NL + "\t\t";
  protected final String TEXT_578 = " ";
  protected final String TEXT_579 = " = (";
  protected final String TEXT_580 = ")eVirtualGet(";
  protected final String TEXT_581 = ");";
  protected final String TEXT_582 = NL + "\t\tif (new";
  protected final String TEXT_583 = " != ((Data";
  protected final String TEXT_584 = ")data).";
  protected final String TEXT_585 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_586 = " msgs = null;" + NL + "\t\t\tif (((Data";
  protected final String TEXT_587 = ")data).";
  protected final String TEXT_588 = " != null)";
  protected final String TEXT_589 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_590 = ") ((Data";
  protected final String TEXT_591 = ")data).";
  protected final String TEXT_592 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_593 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_594 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_595 = ")new";
  protected final String TEXT_596 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_597 = ", null, msgs);";
  protected final String TEXT_598 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_599 = ") ((Data";
  protected final String TEXT_600 = ")data).";
  protected final String TEXT_601 = ").eInverseRemove(this, ";
  protected final String TEXT_602 = ", ";
  protected final String TEXT_603 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_604 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_605 = ")new";
  protected final String TEXT_606 = ").eInverseAdd(this, ";
  protected final String TEXT_607 = ", ";
  protected final String TEXT_608 = ".class, msgs);";
  protected final String TEXT_609 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_610 = "(";
  protected final String TEXT_611 = "new";
  protected final String TEXT_612 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_613 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_614 = NL + "\t\t\tboolean old";
  protected final String TEXT_615 = "ESet = eVirtualIsSet(";
  protected final String TEXT_616 = ");";
  protected final String TEXT_617 = NL + "\t\t\tboolean old";
  protected final String TEXT_618 = "ESet = (";
  protected final String TEXT_619 = " & ";
  protected final String TEXT_620 = "_ESETFLAG) != 0;";
  protected final String TEXT_621 = NL + "\t\t\t";
  protected final String TEXT_622 = " |= ";
  protected final String TEXT_623 = "_ESETFLAG;";
  protected final String TEXT_624 = NL + "\t\t\tboolean old";
  protected final String TEXT_625 = "ESet = ";
  protected final String TEXT_626 = "ESet;";
  protected final String TEXT_627 = NL + "\t\t\t";
  protected final String TEXT_628 = "ESet = true;";
  protected final String TEXT_629 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_630 = "(this, ";
  protected final String TEXT_631 = ".SET, ";
  protected final String TEXT_632 = ", new";
  protected final String TEXT_633 = ", new";
  protected final String TEXT_634 = ", !old";
  protected final String TEXT_635 = "ESet));";
  protected final String TEXT_636 = NL + "\t\t}";
  protected final String TEXT_637 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_638 = "(this, ";
  protected final String TEXT_639 = ".SET, ";
  protected final String TEXT_640 = ", new";
  protected final String TEXT_641 = ", new";
  protected final String TEXT_642 = "));";
  protected final String TEXT_643 = NL + "\t\t";
  protected final String TEXT_644 = " old";
  protected final String TEXT_645 = " = (";
  protected final String TEXT_646 = " & Data";
  protected final String TEXT_647 = ".";
  protected final String TEXT_648 = "_EFLAG) != 0;";
  protected final String TEXT_649 = NL + "\t\t";
  protected final String TEXT_650 = " old";
  protected final String TEXT_651 = " = ";
  protected final String TEXT_652 = "_EFLAG_VALUES[(";
  protected final String TEXT_653 = " & Data";
  protected final String TEXT_654 = ".";
  protected final String TEXT_655 = "_EFLAG) >>> ";
  protected final String TEXT_656 = "_EFLAG_OFFSET];";
  protected final String TEXT_657 = NL + "\t\tif (new";
  protected final String TEXT_658 = ") ";
  protected final String TEXT_659 = " |= Data";
  protected final String TEXT_660 = ".";
  protected final String TEXT_661 = "_EFLAG; else ";
  protected final String TEXT_662 = " &= ~Data";
  protected final String TEXT_663 = ".";
  protected final String TEXT_664 = "_EFLAG;";
  protected final String TEXT_665 = NL + "\t\tif (new";
  protected final String TEXT_666 = " == null) new";
  protected final String TEXT_667 = " = ";
  protected final String TEXT_668 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_669 = " = ";
  protected final String TEXT_670 = " & ~Data";
  protected final String TEXT_671 = ".";
  protected final String TEXT_672 = "_EFLAG | ";
  protected final String TEXT_673 = "new";
  protected final String TEXT_674 = ".ordinal()";
  protected final String TEXT_675 = ".VALUES.indexOf(new";
  protected final String TEXT_676 = ")";
  protected final String TEXT_677 = " << ";
  protected final String TEXT_678 = "_EFLAG_OFFSET;";
  protected final String TEXT_679 = NL + "\t\t";
  protected final String TEXT_680 = " old";
  protected final String TEXT_681 = " = ((Data";
  protected final String TEXT_682 = ")data).";
  protected final String TEXT_683 = ";";
  protected final String TEXT_684 = NL + "\t\t";
  protected final String TEXT_685 = " ";
  protected final String TEXT_686 = " = new";
  protected final String TEXT_687 = " == null ? ";
  protected final String TEXT_688 = " : new";
  protected final String TEXT_689 = ";";
  protected final String TEXT_690 = NL + "\t\t((Data";
  protected final String TEXT_691 = ")data).";
  protected final String TEXT_692 = " = new";
  protected final String TEXT_693 = " == null ? ((Data";
  protected final String TEXT_694 = ")data).";
  protected final String TEXT_695 = " : new";
  protected final String TEXT_696 = ";";
  protected final String TEXT_697 = NL + "\t\t";
  protected final String TEXT_698 = " ";
  protected final String TEXT_699 = " = ";
  protected final String TEXT_700 = "new";
  protected final String TEXT_701 = ";";
  protected final String TEXT_702 = NL + "\t\t((Data";
  protected final String TEXT_703 = ")data).";
  protected final String TEXT_704 = " = ";
  protected final String TEXT_705 = "new";
  protected final String TEXT_706 = ";";
  protected final String TEXT_707 = NL + "\t\tObject old";
  protected final String TEXT_708 = " = eVirtualSet(";
  protected final String TEXT_709 = ", ";
  protected final String TEXT_710 = ");";
  protected final String TEXT_711 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_712 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_713 = NL + "\t\tboolean old";
  protected final String TEXT_714 = "ESet = (";
  protected final String TEXT_715 = " & ";
  protected final String TEXT_716 = "_ESETFLAG) != 0;";
  protected final String TEXT_717 = NL + "\t\t";
  protected final String TEXT_718 = " |= ";
  protected final String TEXT_719 = "_ESETFLAG;";
  protected final String TEXT_720 = NL + "\t\tboolean old";
  protected final String TEXT_721 = "ESet = ";
  protected final String TEXT_722 = "ESet;";
  protected final String TEXT_723 = NL + "\t\t";
  protected final String TEXT_724 = "ESet = true;";
  protected final String TEXT_725 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_726 = "(this, ";
  protected final String TEXT_727 = ".SET, ";
  protected final String TEXT_728 = ", ";
  protected final String TEXT_729 = "isSetChange ? ";
  protected final String TEXT_730 = " : old";
  protected final String TEXT_731 = "old";
  protected final String TEXT_732 = ", ";
  protected final String TEXT_733 = "new";
  protected final String TEXT_734 = ", ";
  protected final String TEXT_735 = "isSetChange";
  protected final String TEXT_736 = "!old";
  protected final String TEXT_737 = "ESet";
  protected final String TEXT_738 = "));";
  protected final String TEXT_739 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_740 = "(this, ";
  protected final String TEXT_741 = ".SET, ";
  protected final String TEXT_742 = ", ";
  protected final String TEXT_743 = "old";
  protected final String TEXT_744 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_745 = " : old";
  protected final String TEXT_746 = "old";
  protected final String TEXT_747 = ", ";
  protected final String TEXT_748 = "new";
  protected final String TEXT_749 = "((Data";
  protected final String TEXT_750 = ")data).";
  protected final String TEXT_751 = "));";
  protected final String TEXT_752 = NL + "\t\t((";
  protected final String TEXT_753 = ".Internal)((";
  protected final String TEXT_754 = ".Internal.Wrapper)get";
  protected final String TEXT_755 = "()).featureMap()).set(";
  protected final String TEXT_756 = ", ";
  protected final String TEXT_757 = "new ";
  protected final String TEXT_758 = "(";
  protected final String TEXT_759 = "new";
  protected final String TEXT_760 = ")";
  protected final String TEXT_761 = ");";
  protected final String TEXT_762 = NL + "\t\t((";
  protected final String TEXT_763 = ".Internal)get";
  protected final String TEXT_764 = "()).set(";
  protected final String TEXT_765 = ", ";
  protected final String TEXT_766 = "new ";
  protected final String TEXT_767 = "(";
  protected final String TEXT_768 = "new";
  protected final String TEXT_769 = ")";
  protected final String TEXT_770 = ");";
  protected final String TEXT_771 = NL + "\t\t";
  protected final String TEXT_772 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_773 = "' ";
  protected final String TEXT_774 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_775 = NL + "\t}" + NL;
  protected final String TEXT_776 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY3" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_777 = NL + "\tpublic ";
  protected final String TEXT_778 = " basicUnset";
  protected final String TEXT_779 = "(";
  protected final String TEXT_780 = " msgs)" + NL + "\t{";
  protected final String TEXT_781 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_782 = ")";
  protected final String TEXT_783 = "basicGet";
  protected final String TEXT_784 = "(), ";
  protected final String TEXT_785 = ", msgs);";
  protected final String TEXT_786 = "Object old";
  protected final String TEXT_787 = " = ";
  protected final String TEXT_788 = "eVirtualUnset(";
  protected final String TEXT_789 = ");";
  protected final String TEXT_790 = NL + "\t\t";
  protected final String TEXT_791 = " old";
  protected final String TEXT_792 = " = ";
  protected final String TEXT_793 = ";";
  protected final String TEXT_794 = NL + "\t\t";
  protected final String TEXT_795 = " = null;";
  protected final String TEXT_796 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_797 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_798 = NL + "\t\tboolean old";
  protected final String TEXT_799 = "ESet = (";
  protected final String TEXT_800 = " & ";
  protected final String TEXT_801 = "_ESETFLAG) != 0;";
  protected final String TEXT_802 = NL + "\t\t";
  protected final String TEXT_803 = " &= ~";
  protected final String TEXT_804 = "_ESETFLAG;";
  protected final String TEXT_805 = NL + "\t\tboolean old";
  protected final String TEXT_806 = "ESet = ";
  protected final String TEXT_807 = "ESet;";
  protected final String TEXT_808 = NL + "\t\t";
  protected final String TEXT_809 = "ESet = false;";
  protected final String TEXT_810 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_811 = " notification = new ";
  protected final String TEXT_812 = "(this, ";
  protected final String TEXT_813 = ".UNSET, ";
  protected final String TEXT_814 = ", ";
  protected final String TEXT_815 = "isSetChange ? old";
  protected final String TEXT_816 = " : null";
  protected final String TEXT_817 = "old";
  protected final String TEXT_818 = ", null, ";
  protected final String TEXT_819 = "isSetChange";
  protected final String TEXT_820 = "old";
  protected final String TEXT_821 = "ESet";
  protected final String TEXT_822 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_823 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_824 = "' ";
  protected final String TEXT_825 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_826 = NL + "\t}" + NL;
  protected final String TEXT_827 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_828 = "#";
  protected final String TEXT_829 = " <em>";
  protected final String TEXT_830 = "</em>}' ";
  protected final String TEXT_831 = ".";
  protected final String TEXT_832 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY4" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_833 = NL + "\t * @see #isSet";
  protected final String TEXT_834 = "()";
  protected final String TEXT_835 = NL + "\t * @see #";
  protected final String TEXT_836 = "()";
  protected final String TEXT_837 = NL + "\t * @see #set";
  protected final String TEXT_838 = "(";
  protected final String TEXT_839 = ")";
  protected final String TEXT_840 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_841 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY5" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_842 = NL + "\tvoid unset";
  protected final String TEXT_843 = "();" + NL;
  protected final String TEXT_844 = NL + "\tpublic void unset";
  protected final String TEXT_845 = "_";
  protected final String TEXT_846 = "()" + NL + "\t{";
  protected final String TEXT_847 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_848 = ", ";
  protected final String TEXT_849 = ");";
  protected final String TEXT_850 = NL + "\t\teUnset(";
  protected final String TEXT_851 = ");";
  protected final String TEXT_852 = NL + "\t\t";
  protected final String TEXT_853 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_854 = NL + "\t\t";
  protected final String TEXT_855 = " ";
  protected final String TEXT_856 = " = (";
  protected final String TEXT_857 = ")eVirtualGet(";
  protected final String TEXT_858 = ");";
  protected final String TEXT_859 = NL + "\t\tif (((Data";
  protected final String TEXT_860 = ")data).";
  protected final String TEXT_861 = " != null) ((";
  protected final String TEXT_862 = ".Unsettable";
  protected final String TEXT_863 = ")";
  protected final String TEXT_864 = ").unset();";
  protected final String TEXT_865 = NL + "\t\t";
  protected final String TEXT_866 = " ";
  protected final String TEXT_867 = " = (";
  protected final String TEXT_868 = ")eVirtualGet(";
  protected final String TEXT_869 = ");";
  protected final String TEXT_870 = NL + "\t\tif (((Data";
  protected final String TEXT_871 = ")data).";
  protected final String TEXT_872 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_873 = " msgs = null;";
  protected final String TEXT_874 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_875 = ")((Data";
  protected final String TEXT_876 = ")data).";
  protected final String TEXT_877 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_878 = ", null, msgs);";
  protected final String TEXT_879 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_880 = ")((Data";
  protected final String TEXT_881 = ")data).";
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
  protected final String TEXT_979 = " : ";
  protected final String TEXT_980 = "old";
  protected final String TEXT_981 = ", ";
  protected final String TEXT_982 = ", ";
  protected final String TEXT_983 = "isSetChange";
  protected final String TEXT_984 = "old";
  protected final String TEXT_985 = "ESet";
  protected final String TEXT_986 = "));";
  protected final String TEXT_987 = NL + "\t\t((";
  protected final String TEXT_988 = ".Internal)((";
  protected final String TEXT_989 = ".Internal.Wrapper)get";
  protected final String TEXT_990 = "()).featureMap()).clear(";
  protected final String TEXT_991 = ");";
  protected final String TEXT_992 = NL + "\t\t((";
  protected final String TEXT_993 = ".Internal)get";
  protected final String TEXT_994 = "()).clear(";
  protected final String TEXT_995 = ");";
  protected final String TEXT_996 = NL + "\t\t";
  protected final String TEXT_997 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_998 = "' ";
  protected final String TEXT_999 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1000 = NL + "\t}" + NL;
  protected final String TEXT_1001 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1002 = "#";
  protected final String TEXT_1003 = " <em>";
  protected final String TEXT_1004 = "</em>}' ";
  protected final String TEXT_1005 = " is set.";
  protected final String TEXT_1006 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY6" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1007 = "</em>' ";
  protected final String TEXT_1008 = " is set.";
  protected final String TEXT_1009 = NL + "\t * @see #unset";
  protected final String TEXT_1010 = "()";
  protected final String TEXT_1011 = NL + "\t * @see #";
  protected final String TEXT_1012 = "()";
  protected final String TEXT_1013 = NL + "\t * @see #set";
  protected final String TEXT_1014 = "(";
  protected final String TEXT_1015 = ")";
  protected final String TEXT_1016 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1017 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY7" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1018 = NL + "\tboolean isSet";
  protected final String TEXT_1019 = "();" + NL;
  protected final String TEXT_1020 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1021 = "_";
  protected final String TEXT_1022 = "()" + NL + "\t{";
  protected final String TEXT_1023 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1024 = ", ";
  protected final String TEXT_1025 = ");";
  protected final String TEXT_1026 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1027 = ");";
  protected final String TEXT_1028 = NL + "\t\treturn ";
  protected final String TEXT_1029 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1030 = NL + "\t\t";
  protected final String TEXT_1031 = " ";
  protected final String TEXT_1032 = " = (";
  protected final String TEXT_1033 = ")eVirtualGet(";
  protected final String TEXT_1034 = ");";
  protected final String TEXT_1035 = NL + "\t\treturn ";
  protected final String TEXT_1036 = " != null && ((";
  protected final String TEXT_1037 = ".Unsettable";
  protected final String TEXT_1038 = ")";
  protected final String TEXT_1039 = ").isSet();";
  protected final String TEXT_1040 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1041 = ");";
  protected final String TEXT_1042 = NL + "\t\treturn (";
  protected final String TEXT_1043 = " & ";
  protected final String TEXT_1044 = "_ESETFLAG) != 0;";
  protected final String TEXT_1045 = NL + "\t\treturn ";
  protected final String TEXT_1046 = "ESet;";
  protected final String TEXT_1047 = NL + "\t\treturn !((";
  protected final String TEXT_1048 = ".Internal)((";
  protected final String TEXT_1049 = ".Internal.Wrapper)get";
  protected final String TEXT_1050 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1051 = ");";
  protected final String TEXT_1052 = NL + "\t\treturn !((";
  protected final String TEXT_1053 = ".Internal)get";
  protected final String TEXT_1054 = "()).isEmpty(";
  protected final String TEXT_1055 = ");";
  protected final String TEXT_1056 = NL + "\t\t";
  protected final String TEXT_1057 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1058 = "' ";
  protected final String TEXT_1059 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1060 = NL + "\t}" + NL;
  protected final String TEXT_1061 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1062 = "(";
  protected final String TEXT_1063 = ") <em>";
  protected final String TEXT_1064 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1065 = "(";
  protected final String TEXT_1066 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1067 = " ";
  protected final String TEXT_1068 = "__EEXPRESSION = \"";
  protected final String TEXT_1069 = "\";";
  protected final String TEXT_1070 = NL;
  protected final String TEXT_1071 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1072 = "(";
  protected final String TEXT_1073 = ") <em>";
  protected final String TEXT_1074 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1075 = "(";
  protected final String TEXT_1076 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1077 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1078 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1079 = ".Internal)";
  protected final String TEXT_1080 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1081 = NL + "\t/**";
  protected final String TEXT_1082 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY10" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1083 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1084 = NL + "\t * ";
  protected final String TEXT_1085 = NL + "\t * @param ";
  protected final String TEXT_1086 = NL + "\t *   ";
  protected final String TEXT_1087 = NL + "\t * @param ";
  protected final String TEXT_1088 = " ";
  protected final String TEXT_1089 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1090 = NL + "\t * @model ";
  protected final String TEXT_1091 = NL + "\t *        ";
  protected final String TEXT_1092 = NL + "\t * @model";
  protected final String TEXT_1093 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1094 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY11" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1095 = NL + "\t";
  protected final String TEXT_1096 = " ";
  protected final String TEXT_1097 = "(";
  protected final String TEXT_1098 = ")";
  protected final String TEXT_1099 = ";" + NL;
  protected final String TEXT_1100 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1101 = NL + "\tpublic ";
  protected final String TEXT_1102 = " ";
  protected final String TEXT_1103 = "(";
  protected final String TEXT_1104 = ")";
  protected final String TEXT_1105 = NL + "\t{";
  protected final String TEXT_1106 = NL + "\t\t";
  protected final String TEXT_1107 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1108 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1109 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1110 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1111 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1112 = "\",";
  protected final String TEXT_1113 = NL + "\t\t\t\t ";
  protected final String TEXT_1114 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1115 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1116 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1117 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1118 = ".";
  protected final String TEXT_1119 = ");";
  protected final String TEXT_1120 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1121 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1122 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1123 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1124 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1125 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1126 = ".";
  protected final String TEXT_1127 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1128 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1129 = "\", ";
  protected final String TEXT_1130 = ".getObjectLabel(this, ";
  protected final String TEXT_1131 = ") }),";
  protected final String TEXT_1132 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1133 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1134 = NL + "\t\t\t";
  protected final String TEXT_1135 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1136 = "new ";
  protected final String TEXT_1137 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1138 = ", ";
  protected final String TEXT_1139 = ")";
  protected final String TEXT_1140 = "null";
  protected final String TEXT_1141 = ");";
  protected final String TEXT_1142 = NL + "\t\t\treturn ";
  protected final String TEXT_1143 = "(";
  protected final String TEXT_1144 = "(";
  protected final String TEXT_1145 = ")";
  protected final String TEXT_1146 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1147 = "new ";
  protected final String TEXT_1148 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1149 = ", ";
  protected final String TEXT_1150 = ")";
  protected final String TEXT_1151 = "null";
  protected final String TEXT_1152 = ")";
  protected final String TEXT_1153 = ").";
  protected final String TEXT_1154 = "()";
  protected final String TEXT_1155 = ";";
  protected final String TEXT_1156 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1157 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1158 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1159 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1160 = NL + "\t}" + NL;
  protected final String TEXT_1161 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY12" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1162 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1163 = NL + "\t@Override";
  protected final String TEXT_1164 = NL + "\tpublic ";
  protected final String TEXT_1165 = " eInverseAdd(";
  protected final String TEXT_1166 = " otherEnd, int featureID, ";
  protected final String TEXT_1167 = " msgs)" + NL + "\t{" + NL + "\t\tif (data==null) data = new Data";
  protected final String TEXT_1168 = "();" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1169 = ")" + NL + "\t\t{";
  protected final String TEXT_1170 = NL + "\t\t\tcase ";
  protected final String TEXT_1171 = ":";
  protected final String TEXT_1172 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1173 = "(";
  protected final String TEXT_1174 = ".InternalMapView";
  protected final String TEXT_1175 = ")";
  protected final String TEXT_1176 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1177 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1178 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1179 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1180 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1181 = "((";
  protected final String TEXT_1182 = ")otherEnd, msgs);";
  protected final String TEXT_1183 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1184 = ", msgs);";
  protected final String TEXT_1185 = NL + "\t\t\t\t";
  protected final String TEXT_1186 = " ";
  protected final String TEXT_1187 = " = (";
  protected final String TEXT_1188 = ")eVirtualGet(";
  protected final String TEXT_1189 = ");";
  protected final String TEXT_1190 = NL + "\t\t\t\t";
  protected final String TEXT_1191 = " ";
  protected final String TEXT_1192 = " = ";
  protected final String TEXT_1193 = "basicGet";
  protected final String TEXT_1194 = "();";
  protected final String TEXT_1195 = NL + "\t\t\t\tif (((Data";
  protected final String TEXT_1196 = ")data).";
  protected final String TEXT_1197 = " != null)";
  protected final String TEXT_1198 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1199 = ")((Data";
  protected final String TEXT_1200 = ")data).";
  protected final String TEXT_1201 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1202 = ", null, msgs);";
  protected final String TEXT_1203 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1204 = ")((Data";
  protected final String TEXT_1205 = ")data).";
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
  protected final String TEXT_1368 = NL + "/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY18" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
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
  protected final String TEXT_1428 = ", ";
  protected final String TEXT_1429 = ") != ";
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
  protected final String TEXT_1446 = ", ";
  protected final String TEXT_1447 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1448 = " == null ? ";
  protected final String TEXT_1449 = "() != null : !";
  protected final String TEXT_1450 = ".equals(";
  protected final String TEXT_1451 = "());";
  protected final String TEXT_1452 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1453 = ".";
  protected final String TEXT_1454 = " == null ? ";
  protected final String TEXT_1455 = "() != null : !Data";
  protected final String TEXT_1456 = ".";
  protected final String TEXT_1457 = ".equals(";
  protected final String TEXT_1458 = "());";
  protected final String TEXT_1459 = NL + "\t\t}";
  protected final String TEXT_1460 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1461 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1462 = NL + "\t}" + NL + NL;
  protected final String TEXT_1463 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY19" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1464 = NL + "\t@Override";
  protected final String TEXT_1465 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1466 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1467 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1468 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1469 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1470 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1471 = ": return ";
  protected final String TEXT_1472 = ";";
  protected final String TEXT_1473 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1474 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1475 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY20" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1476 = NL + "\t@Override";
  protected final String TEXT_1477 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1478 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1479 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1480 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1481 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1482 = ": return ";
  protected final String TEXT_1483 = ";";
  protected final String TEXT_1484 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1485 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1486 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1487 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1488 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1489 = ": return ";
  protected final String TEXT_1490 = ";";
  protected final String TEXT_1491 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1492 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1493 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY21" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1494 = NL + "\t@Override";
  protected final String TEXT_1495 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1496 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1497 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1498 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1499 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1500 = ": return ";
  protected final String TEXT_1501 = ";";
  protected final String TEXT_1502 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1503 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1504 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1505 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1506 = ": return ";
  protected final String TEXT_1507 = ";";
  protected final String TEXT_1508 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1509 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1510 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1511 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1512 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1513 = ": return ";
  protected final String TEXT_1514 = ";";
  protected final String TEXT_1515 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1516 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1517 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY22" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1518 = NL + "\t@Override";
  protected final String TEXT_1519 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1520 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY23" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1521 = NL + "\t@Override";
  protected final String TEXT_1522 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1523 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1524 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY24" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1525 = NL + "\t@Override";
  protected final String TEXT_1526 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1527 = NL + "\t\t\tcase ";
  protected final String TEXT_1528 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1529 = ";";
  protected final String TEXT_1530 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY25" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1531 = NL + "\t@Override";
  protected final String TEXT_1532 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1533 = NL + "\t\t\tcase ";
  protected final String TEXT_1534 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1535 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1536 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1537 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY26" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1538 = NL + "\t@Override";
  protected final String TEXT_1539 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1540 = "\"unchecked\"";
  protected final String TEXT_1541 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1542 = ")";
  protected final String TEXT_1543 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1544 = " arguments) throws ";
  protected final String TEXT_1545 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1546 = ")" + NL + "\t\t{";
  protected final String TEXT_1547 = NL + "\t\t\tcase ";
  protected final String TEXT_1548 = ":";
  protected final String TEXT_1549 = NL + "\t\t\t\t";
  protected final String TEXT_1550 = "(";
  protected final String TEXT_1551 = "(";
  protected final String TEXT_1552 = "(";
  protected final String TEXT_1553 = ")";
  protected final String TEXT_1554 = "arguments.get(";
  protected final String TEXT_1555 = ")";
  protected final String TEXT_1556 = ").";
  protected final String TEXT_1557 = "()";
  protected final String TEXT_1558 = ", ";
  protected final String TEXT_1559 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1560 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1561 = "new ";
  protected final String TEXT_1562 = "(";
  protected final String TEXT_1563 = "(";
  protected final String TEXT_1564 = "(";
  protected final String TEXT_1565 = "(";
  protected final String TEXT_1566 = ")";
  protected final String TEXT_1567 = "arguments.get(";
  protected final String TEXT_1568 = ")";
  protected final String TEXT_1569 = ").";
  protected final String TEXT_1570 = "()";
  protected final String TEXT_1571 = ", ";
  protected final String TEXT_1572 = ")";
  protected final String TEXT_1573 = ")";
  protected final String TEXT_1574 = ";";
  protected final String TEXT_1575 = NL + "\t\t}";
  protected final String TEXT_1576 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1577 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1578 = NL + "\t}" + NL;
  protected final String TEXT_1579 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY27" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1580 = NL + "\t@Override";
  protected final String TEXT_1581 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());" + NL + "\t\tif (data != null) result.append(((Data";
  protected final String TEXT_1582 = ")data).toString());" + NL + "\t\t" + NL + "\t\treturn result.toString();" + NL + "\t\t}";
  protected final String TEXT_1583 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY28" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1584 = NL + "\t@";
  protected final String TEXT_1585 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY29" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1586 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY30" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY31" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1587 = " getKey()" + NL + "\t{";
  protected final String TEXT_1588 = NL + "\t\treturn new ";
  protected final String TEXT_1589 = "(getTypedKey());";
  protected final String TEXT_1590 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1591 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY32" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1592 = " key)" + NL + "\t{";
  protected final String TEXT_1593 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1594 = "(";
  protected final String TEXT_1595 = ")";
  protected final String TEXT_1596 = "key);";
  protected final String TEXT_1597 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1598 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1599 = ")key).";
  protected final String TEXT_1600 = "());";
  protected final String TEXT_1601 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1602 = ")key);";
  protected final String TEXT_1603 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY33" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1604 = " getValue()" + NL + "\t{";
  protected final String TEXT_1605 = NL + "\t\treturn new ";
  protected final String TEXT_1606 = "(getTypedValue());";
  protected final String TEXT_1607 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1608 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY34" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1609 = " setValue(";
  protected final String TEXT_1610 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1611 = " oldValue = getValue();";
  protected final String TEXT_1612 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1613 = "(";
  protected final String TEXT_1614 = ")";
  protected final String TEXT_1615 = "value);";
  protected final String TEXT_1616 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1617 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1618 = ")value).";
  protected final String TEXT_1619 = "());";
  protected final String TEXT_1620 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1621 = ")value);";
  protected final String TEXT_1622 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY35" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1623 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1624 = NL + "\tpublic ";
  protected final String TEXT_1625 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1626 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1627 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1628 = NL + NL + NL + NL + "// data Class generation ";
  protected final String TEXT_1629 = NL + "protected static  class Data";
  protected final String TEXT_1630 = NL + NL + "{" + NL;
  protected final String TEXT_1631 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_1632 = " copyright = ";
  protected final String TEXT_1633 = ";";
  protected final String TEXT_1634 = NL;
  protected final String TEXT_1635 = NL;
  protected final String TEXT_1636 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_1637 = "() <em>";
  protected final String TEXT_1638 = "</em>}' ";
  protected final String TEXT_1639 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1640 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1641 = NL + "\t@";
  protected final String TEXT_1642 = NL + "\tprotected ";
  protected final String TEXT_1643 = ".Internal.SettingDelegate ";
  protected final String TEXT_1644 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_1645 = ".Internal)";
  protected final String TEXT_1646 = ").getSettingDelegate();" + NL;
  protected final String TEXT_1647 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1648 = "() <em>";
  protected final String TEXT_1649 = "</em>}' ";
  protected final String TEXT_1650 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1651 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1652 = NL + "\t@";
  protected final String TEXT_1653 = NL + "\tprotected ";
  protected final String TEXT_1654 = " ";
  protected final String TEXT_1655 = ";" + NL;
  protected final String TEXT_1656 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_1657 = "() <em>";
  protected final String TEXT_1658 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1659 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1660 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1661 = NL + "\tprotected static final ";
  protected final String TEXT_1662 = "[] ";
  protected final String TEXT_1663 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_1664 = " [0]";
  protected final String TEXT_1665 = ";" + NL;
  protected final String TEXT_1666 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_1667 = "() <em>";
  protected final String TEXT_1668 = "</em>}' ";
  protected final String TEXT_1669 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1670 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1671 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1672 = NL + "\tprotected static final ";
  protected final String TEXT_1673 = " ";
  protected final String TEXT_1674 = "; // TODO The default value literal \"";
  protected final String TEXT_1675 = "\" is not valid.";
  protected final String TEXT_1676 = " = ";
  protected final String TEXT_1677 = ";";
  protected final String TEXT_1678 = NL;
  protected final String TEXT_1679 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1680 = NL + "\t@";
  protected final String TEXT_1681 = NL + "\tprotected int ";
  protected final String TEXT_1682 = " = 0;" + NL;
  protected final String TEXT_1683 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_1684 = "() <em>";
  protected final String TEXT_1685 = "</em>}' ";
  protected final String TEXT_1686 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1687 = "_EFLAG_OFFSET = ";
  protected final String TEXT_1688 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_1689 = "() <em>";
  protected final String TEXT_1690 = "</em>}' ";
  protected final String TEXT_1691 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1692 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_1693 = ".ordinal()";
  protected final String TEXT_1694 = ".VALUES.indexOf(";
  protected final String TEXT_1695 = ")";
  protected final String TEXT_1696 = " << ";
  protected final String TEXT_1697 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_1698 = " ";
  protected final String TEXT_1699 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_1700 = "[] ";
  protected final String TEXT_1701 = "_EFLAG_VALUES = ";
  protected final String TEXT_1702 = ".values()";
  protected final String TEXT_1703 = "(";
  protected final String TEXT_1704 = "[])";
  protected final String TEXT_1705 = ".VALUES.toArray(new ";
  protected final String TEXT_1706 = "[";
  protected final String TEXT_1707 = ".VALUES.size()])";
  protected final String TEXT_1708 = ";" + NL;
  protected final String TEXT_1709 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_1710 = " representing the value of the '{@link #";
  protected final String TEXT_1711 = "() <em>";
  protected final String TEXT_1712 = "</em>}' ";
  protected final String TEXT_1713 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1714 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1715 = "_EFLAG = ";
  protected final String TEXT_1716 = " << ";
  protected final String TEXT_1717 = "_EFLAG_OFFSET";
  protected final String TEXT_1718 = ";" + NL;
  protected final String TEXT_1719 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1720 = "() <em>";
  protected final String TEXT_1721 = "</em>}' ";
  protected final String TEXT_1722 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1723 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1724 = NL + "\t@";
  protected final String TEXT_1725 = NL + "\tprotected ";
  protected final String TEXT_1726 = " ";
  protected final String TEXT_1727 = " = ";
  protected final String TEXT_1728 = ";" + NL;
  protected final String TEXT_1729 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1730 = NL + "\t@";
  protected final String TEXT_1731 = NL + "\tprotected int ";
  protected final String TEXT_1732 = " = 0;" + NL;
  protected final String TEXT_1733 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_1734 = " ";
  protected final String TEXT_1735 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1736 = "_ESETFLAG = 1 << ";
  protected final String TEXT_1737 = ";" + NL;
  protected final String TEXT_1738 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_1739 = " ";
  protected final String TEXT_1740 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1741 = NL + "\t@";
  protected final String TEXT_1742 = NL + "\tprotected boolean ";
  protected final String TEXT_1743 = "ESet;" + NL;
  protected final String TEXT_1744 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_1745 = ".getOperationID(";
  protected final String TEXT_1746 = ") - ";
  protected final String TEXT_1747 = ";" + NL;
  protected final String TEXT_1748 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1749 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Data";
  protected final String TEXT_1750 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_1751 = NL + "\t\t";
  protected final String TEXT_1752 = " |= ";
  protected final String TEXT_1753 = "_EFLAG";
  protected final String TEXT_1754 = "_DEFAULT";
  protected final String TEXT_1755 = ";";
  protected final String TEXT_1756 = NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString(){\t" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1757 = "\t\t";
  protected final String TEXT_1758 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1759 = ": \");";
  protected final String TEXT_1760 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1761 = ": \");";
  protected final String TEXT_1762 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1763 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1764 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1765 = NL + "\t\tif (";
  protected final String TEXT_1766 = "(";
  protected final String TEXT_1767 = " & ";
  protected final String TEXT_1768 = "_ESETFLAG) != 0";
  protected final String TEXT_1769 = "ESet";
  protected final String TEXT_1770 = ") result.append((";
  protected final String TEXT_1771 = " & ";
  protected final String TEXT_1772 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1773 = NL + "\t\tif (";
  protected final String TEXT_1774 = "(";
  protected final String TEXT_1775 = " & ";
  protected final String TEXT_1776 = "_ESETFLAG) != 0";
  protected final String TEXT_1777 = "ESet";
  protected final String TEXT_1778 = ") result.append(";
  protected final String TEXT_1779 = "_EFLAG_VALUES[(";
  protected final String TEXT_1780 = " & ";
  protected final String TEXT_1781 = "_EFLAG) >>> ";
  protected final String TEXT_1782 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1783 = NL + "\t\tif (";
  protected final String TEXT_1784 = "(";
  protected final String TEXT_1785 = " & ";
  protected final String TEXT_1786 = "_ESETFLAG) != 0";
  protected final String TEXT_1787 = "ESet";
  protected final String TEXT_1788 = ") result.append(";
  protected final String TEXT_1789 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1790 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1791 = ", ";
  protected final String TEXT_1792 = "));";
  protected final String TEXT_1793 = NL + "\t\tresult.append((";
  protected final String TEXT_1794 = " & ";
  protected final String TEXT_1795 = "_EFLAG) != 0);";
  protected final String TEXT_1796 = NL + "\t\tresult.append(";
  protected final String TEXT_1797 = "_EFLAG_VALUES[(";
  protected final String TEXT_1798 = " & ";
  protected final String TEXT_1799 = "_EFLAG) >>> ";
  protected final String TEXT_1800 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1801 = NL + "\t\tresult.append(";
  protected final String TEXT_1802 = ");";
  protected final String TEXT_1803 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL + "\t\t";
  protected final String TEXT_1804 = NL + "}";
  protected final String TEXT_1805 = NL + "} //";
  protected final String TEXT_1806 = NL;

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
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_267);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_270);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_274);
    } else {
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_279);
    }
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_282);
    } 	  	  
	  else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_285);
    } 	  
	  else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_291);
    }
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_295);
    }
    stringBuffer.append(TEXT_296);
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_300);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_304);
    } else {
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_312);
    }
    } else {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_316);
    }
    }
    } 	
	else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_326);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_330);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_331);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_333);
    } else {
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_335);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_338);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_340);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_342);
    } else {
    stringBuffer.append(TEXT_343);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_345);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_346);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_347);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_349);
    }
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_351);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_353);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_355);
    }
    stringBuffer.append(TEXT_356);
    } else {
    stringBuffer.append(TEXT_357);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_358);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_360);
    }
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
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_371);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_372);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_373);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_374);
    } else {
    stringBuffer.append(TEXT_375);
    }
    stringBuffer.append(TEXT_376);
    }
    stringBuffer.append(TEXT_377);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_378);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_379);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_382);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_386);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_387);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_388);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_389);
    }
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_392);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_394);
    }
    stringBuffer.append(TEXT_395);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_398);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_401);
    } else {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_404);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_407);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_409);
    } else {
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_411);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_413);
    }
    } else {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_416);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_417);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_418);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_424);
     if (! genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && ! genFeature.hasSettingDelegate() && ! genFeature.isContainer() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_426);
    }
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_430);
    stringBuffer.append(TEXT_431);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_435);
    stringBuffer.append(TEXT_436);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_440);
    } else {
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_448);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_450);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_454);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_457);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_460);
    }
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_462);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_463);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_468);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_472);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_473);
    } else {
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_475);
    }
    stringBuffer.append(TEXT_476);
    } else {
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_481);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_486);
    }
    stringBuffer.append(TEXT_487);
    }
    stringBuffer.append(TEXT_488);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_491);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_494);
    } else {
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_499);
    }
    } else {
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_502);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_503);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_511);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_514);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_516);
    }
    }
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_518);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_519);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_522);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_524);
    }
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_526);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(TEXT_528);
     if (!genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && !genFeature.hasSettingDelegate() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_530);
    }
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_533);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_535);
    }
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_537);
    }
    stringBuffer.append(TEXT_538);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_540);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_542);
    }
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_544);
    }
    stringBuffer.append(TEXT_545);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getUpperName());
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
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_565);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_566);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_570);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_576);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_581);
    }
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_588);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_597);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_602);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_607);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_608);
    }
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_612);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_613);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_616);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_620);
    }
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_623);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_626);
    }
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_628);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_635);
    }
    stringBuffer.append(TEXT_636);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_642);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_648);
    } else {
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_656);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_664);
    } else {
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_672);
    if (isJDK50) {
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_674);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_676);
    }
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_678);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_683);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_689);
    } else {
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_696);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_701);
    } else {
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_706);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_710);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_712);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_716);
    }
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_719);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_722);
    }
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_724);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_728);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_732);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_734);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_735);
    } else {
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_737);
    }
    stringBuffer.append(TEXT_738);
    }
    } else {
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_747);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_751);
    }
    }
    }
    } 
	else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_754);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_756);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_758);
    }
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_760);
    }
    stringBuffer.append(TEXT_761);
    } else {
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_763);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_765);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_767);
    }
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_769);
    }
    stringBuffer.append(TEXT_770);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_771);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_774);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_775);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_776);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_780);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_782);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_785);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_787);
    }
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_789);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_793);
    }
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_795);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_797);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_801);
    }
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_804);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_807);
    }
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_809);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_814);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_816);
    } else {
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_818);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_819);
    } else {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_821);
    }
    stringBuffer.append(TEXT_822);
    }
    } else {
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_825);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_826);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_831);
    stringBuffer.append(TEXT_832);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_834);
    }
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_836);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_839);
    }
    stringBuffer.append(TEXT_840);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_841);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_843);
    } else {
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_845);
    }
    stringBuffer.append(TEXT_846);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_849);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_851);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_853);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_858);
    }
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_864);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_869);
    }
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_873);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_878);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_881);
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
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_982);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_983);
    } else {
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_985);
    }
    stringBuffer.append(TEXT_986);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_987);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_989);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_991);
    } else {
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_993);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_995);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_999);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1000);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1008);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1010);
    }
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1012);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1015);
    }
    stringBuffer.append(TEXT_1016);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1017);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1019);
    } else {
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1021);
    }
    stringBuffer.append(TEXT_1022);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1025);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1027);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1029);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1034);
    }
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1039);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1041);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1044);
    } else {
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1046);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1051);
    } else {
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1055);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1059);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1060);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1070);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1080);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(TEXT_1082);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1083);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1089);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1092);
    }}
    stringBuffer.append(TEXT_1093);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1094);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1099);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1100);
    }
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1105);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1119);
    } else {
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1132);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1133);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1135);
    if (size > 0) {
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1139);
    } else {
    stringBuffer.append(TEXT_1140);
    }
    stringBuffer.append(TEXT_1141);
    } else {
    stringBuffer.append(TEXT_1142);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1143);
    }
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1146);
    if (size > 0) {
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1150);
    } else {
    stringBuffer.append(TEXT_1151);
    }
    stringBuffer.append(TEXT_1152);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1154);
    }
    stringBuffer.append(TEXT_1155);
    }
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1158);
    } else {
    stringBuffer.append(TEXT_1159);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1160);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1161);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1162);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1163);
    }
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1167);
    stringBuffer.append( genClass.getInterfaceName());
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1169);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1171);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1176);
    } else {
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1178);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1179);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1182);
    } else {
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1184);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1189);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1192);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1194);
    }
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1197);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1202);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1205);
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
    stringBuffer.append(genFeature.getEDefault());
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
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1448);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1451);
    } else {
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1454);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1458);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1459);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1460);
    } else {
    stringBuffer.append(TEXT_1461);
    }
    stringBuffer.append(TEXT_1462);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1463);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1464);
    }
    stringBuffer.append(TEXT_1465);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1466);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1469);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1472);
    }
    stringBuffer.append(TEXT_1473);
    }
    stringBuffer.append(TEXT_1474);
    }
    stringBuffer.append(TEXT_1475);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1476);
    }
    stringBuffer.append(TEXT_1477);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1478);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1479);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1480);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1483);
    }
    stringBuffer.append(TEXT_1484);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1487);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1490);
    }
    stringBuffer.append(TEXT_1491);
    }
    stringBuffer.append(TEXT_1492);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1493);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1494);
    }
    stringBuffer.append(TEXT_1495);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1496);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1497);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1498);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1499);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1501);
    }
    }
    stringBuffer.append(TEXT_1502);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1504);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1505);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1506);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1507);
    }
    stringBuffer.append(TEXT_1508);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1510);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1511);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1514);
    }
    stringBuffer.append(TEXT_1515);
    }
    stringBuffer.append(TEXT_1516);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1517);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1518);
    }
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1520);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1521);
    }
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1523);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1524);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1525);
    }
    stringBuffer.append(TEXT_1526);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1528);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1529);
    }
    stringBuffer.append(TEXT_1530);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1531);
    }
    stringBuffer.append(TEXT_1532);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1533);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1534);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1535);
    }
    stringBuffer.append(TEXT_1536);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1537);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1538);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1539);
    if (!isRaw) {
    stringBuffer.append(TEXT_1540);
    } else {
    stringBuffer.append(TEXT_1541);
    }
    stringBuffer.append(TEXT_1542);
    }
    }
    stringBuffer.append(TEXT_1543);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1544);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1545);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1546);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1547);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1548);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1549);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1550);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1551);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1552);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1553);
    }
    stringBuffer.append(TEXT_1554);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1555);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1556);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1557);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1558);
    }
    }
    stringBuffer.append(TEXT_1559);
    } else {
    stringBuffer.append(TEXT_1560);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1562);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1563);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1564);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1565);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1566);
    }
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1568);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1569);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1570);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1571);
    }
    }
    stringBuffer.append(TEXT_1572);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1573);
    }
    stringBuffer.append(TEXT_1574);
    }
    }
    stringBuffer.append(TEXT_1575);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1576);
    } else {
    stringBuffer.append(TEXT_1577);
    }
    stringBuffer.append(TEXT_1578);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1579);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1580);
    }
    stringBuffer.append(TEXT_1581);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1582);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1583);
    if (isGWT) {
    stringBuffer.append(TEXT_1584);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1585);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1586);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1587);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1588);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1589);
    } else {
    stringBuffer.append(TEXT_1590);
    }
    stringBuffer.append(TEXT_1591);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1592);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1593);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1594);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1595);
    }
    stringBuffer.append(TEXT_1596);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1597);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1599);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1600);
    } else {
    stringBuffer.append(TEXT_1601);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1602);
    }
    stringBuffer.append(TEXT_1603);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1604);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1605);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1606);
    } else {
    stringBuffer.append(TEXT_1607);
    }
    stringBuffer.append(TEXT_1608);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1609);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1610);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1611);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1612);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1614);
    }
    stringBuffer.append(TEXT_1615);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1616);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1617);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1618);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1619);
    } else {
    stringBuffer.append(TEXT_1620);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1621);
    }
    stringBuffer.append(TEXT_1622);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1623);
    }
    stringBuffer.append(TEXT_1624);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1625);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1626);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1627);
    }
    stringBuffer.append(TEXT_1628);
    if (isImplementation) {
    stringBuffer.append(TEXT_1629);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(CodegenUtil.getDataClassExtends (genClass));
    stringBuffer.append(TEXT_1630);
     if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_1631);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1632);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_1633);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1634);
    }
    stringBuffer.append(TEXT_1635);
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1636);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1637);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1638);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1640);
    if (isGWT) {
    stringBuffer.append(TEXT_1641);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1642);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1643);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1644);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1645);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1646);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1647);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1648);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1649);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1651);
    if (isGWT) {
    stringBuffer.append(TEXT_1652);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1653);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_1654);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1655);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_1656);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1657);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1658);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1659);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_1660);
    }
    stringBuffer.append(TEXT_1661);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_1662);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1663);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_1664);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_1665);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1667);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1669);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1670);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_1671);
    }
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1673);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_1674);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_1675);
    } else {
    stringBuffer.append(TEXT_1676);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_1677);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_1678);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1679);
    if (isGWT) {
    stringBuffer.append(TEXT_1680);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1682);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_1683);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1684);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1685);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1686);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1687);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1689);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1690);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1691);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1692);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1693);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1694);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1695);
    }
    stringBuffer.append(TEXT_1696);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1697);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_1699);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1700);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1701);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1702);
    } else {
    stringBuffer.append(TEXT_1703);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1704);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1706);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1707);
    }
    stringBuffer.append(TEXT_1708);
    }
    stringBuffer.append(TEXT_1709);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_1710);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1711);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1714);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1715);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_1716);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1717);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_1718);
    } else {
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1720);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1721);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1722);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1723);
    if (isGWT) {
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1725);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1726);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1727);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1728);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1729);
    if (isGWT) {
    stringBuffer.append(TEXT_1730);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1731);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1732);
    }
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1734);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1735);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1736);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_1737);
    } else {
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1739);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1740);
    if (isGWT) {
    stringBuffer.append(TEXT_1741);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1742);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1743);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1744);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1745);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1746);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_1747);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1749);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1750);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_1751);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1752);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1753);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1754);
    }
    stringBuffer.append(TEXT_1755);
    }
    stringBuffer.append(TEXT_1756);
    { boolean first = true;
    stringBuffer.append(TEXT_1757);
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1758);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1759);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1760);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1761);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1762);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1763);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1764);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1765);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1766);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1767);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1768);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1769);
    }
    stringBuffer.append(TEXT_1770);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1771);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1772);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1773);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1774);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1775);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1776);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1777);
    }
    stringBuffer.append(TEXT_1778);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1779);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1780);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1781);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1782);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1783);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1784);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1785);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1786);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1787);
    }
    stringBuffer.append(TEXT_1788);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1789);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1790);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1791);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1792);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1793);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1794);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1795);
    } else {
    stringBuffer.append(TEXT_1796);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1797);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1798);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1799);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1800);
    }
    } else {
    stringBuffer.append(TEXT_1801);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1802);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1803);
    }
    stringBuffer.append(TEXT_1804);
    } 
    stringBuffer.append(TEXT_1805);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1806);
    return stringBuffer.toString();
  }
}
