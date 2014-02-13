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
  protected final String TEXT_2 = "/**" + NL + "<<<<<<< HEAD" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "=======" + NL + " * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes" + NL + " * All rights reserved. This program and the accompanying materials" + NL + " * are made available under the terms of the Eclipse Public License v1.0" + NL + " * which accompanies this distribution, and is available at" + NL + " * http://www.eclipse.org/legal/epl-v10.html" + NL + " * " + NL + " * Contributors:" + NL + " *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation" + NL + " * Descritpion ! To come" + NL + " * @author Amine BENELALLAM" + NL + " **/" + NL + ">>>>>>> e211675... Templates refactoring.";
  protected final String TEXT_5 = NL + "package ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL + "package ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_13 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_14 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_15 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_16 = NL + " *";
  protected final String TEXT_17 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * <ul>";
  protected final String TEXT_18 = NL + " *   <li>{@link ";
  protected final String TEXT_19 = "#";
  protected final String TEXT_20 = " <em>";
  protected final String TEXT_21 = "</em>}</li>";
  protected final String TEXT_22 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_23 = NL + " *";
  protected final String TEXT_24 = NL + " * @see ";
  protected final String TEXT_25 = "#get";
  protected final String TEXT_26 = "()";
  protected final String TEXT_27 = NL + " * @model ";
  protected final String TEXT_28 = NL + " *        ";
  protected final String TEXT_29 = NL + " * @model";
  protected final String TEXT_30 = NL + " * @extends ";
  protected final String TEXT_31 = NL + " * @generated" + NL + " */";
  protected final String TEXT_32 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_33 = "</b></em>'." + NL + " * <!-- end-user-doc -->" + NL + " * <p>";
  protected final String TEXT_34 = NL + " * The following features are implemented:" + NL + " * <ul>";
  protected final String TEXT_35 = NL + " *   <li>{@link ";
  protected final String TEXT_36 = "#";
  protected final String TEXT_37 = " <em>";
  protected final String TEXT_38 = "</em>}</li>";
  protected final String TEXT_39 = NL + " * </ul>";
  protected final String TEXT_40 = NL + " * </p>" + NL + " *" + NL + " * @generated" + NL + " */";
  protected final String TEXT_41 = NL + "public";
  protected final String TEXT_42 = " abstract";
  protected final String TEXT_43 = " class ";
  protected final String TEXT_44 = NL + "public interface ";
  protected final String TEXT_45 = NL + "{";
  protected final String TEXT_46 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_47 = " copyright = ";
  protected final String TEXT_48 = ";";
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
  protected final String TEXT_81 = NL + "\t " + NL + "\t";
  protected final String TEXT_82 = NL + "\t/**" + NL + "\t * The cached value of the data structure {@link Data";
  protected final String TEXT_83 = " <em>data</em> } " + NL + "\t * @generated" + NL + "\t */" + NL + "\t \tprotected Data";
  protected final String TEXT_84 = " data;" + NL + "\t ";
  protected final String TEXT_85 = NL + "\t " + NL + "\t " + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_86 = "public";
  protected final String TEXT_87 = "protected";
  protected final String TEXT_88 = " ";
  protected final String TEXT_89 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t\t";
  protected final String TEXT_90 = NL + "\t\t";
  protected final String TEXT_91 = " |= ";
  protected final String TEXT_92 = "_EFLAG";
  protected final String TEXT_93 = "_DEFAULT";
  protected final String TEXT_94 = ";";
  protected final String TEXT_95 = NL + "\t}" + NL + "\t";
  protected final String TEXT_96 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t ";
  protected final String TEXT_97 = "@Override";
  protected final String TEXT_98 = NL + "\tprotected Data";
  protected final String TEXT_99 = " getData(){" + NL + "\t\tif ( data == null || !(data instanceof Data";
  protected final String TEXT_100 = ")){" + NL + "\t\t\tdata = new Data";
  protected final String TEXT_101 = "();" + NL + "\t\t\tif (isLoaded())" + NL + "\t\t\t((";
  protected final String TEXT_102 = ") this.eResource()).fetchAttributes(this);" + NL + "\t\t\t}" + NL + "\t\treturn (Data";
  protected final String TEXT_103 = ") data;" + NL + "\t}" + NL + "\t";
  protected final String TEXT_104 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_105 = NL + "\t@Override";
  protected final String TEXT_106 = NL + "\tprotected ";
  protected final String TEXT_107 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_108 = ";" + NL + "\t}" + NL;
  protected final String TEXT_109 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_110 = NL + "\t@Override";
  protected final String TEXT_111 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_112 = ";" + NL + "\t}" + NL;
  protected final String TEXT_113 = NL;
  protected final String TEXT_114 = "  ";
  protected final String TEXT_115 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX1" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_116 = NL + "\t";
  protected final String TEXT_117 = "[] ";
  protected final String TEXT_118 = "();" + NL;
  protected final String TEXT_119 = NL + "\tpublic ";
  protected final String TEXT_120 = "[] ";
  protected final String TEXT_121 = "()" + NL + "\t{";
  protected final String TEXT_122 = NL + "\t\t";
  protected final String TEXT_123 = " list = (";
  protected final String TEXT_124 = ")";
  protected final String TEXT_125 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_126 = "(";
  protected final String TEXT_127 = "[])";
  protected final String TEXT_128 = "_EEMPTY_ARRAY;";
  protected final String TEXT_129 = NL + "\t\tif (";
  protected final String TEXT_130 = " == null || ";
  protected final String TEXT_131 = ".isEmpty()) return ";
  protected final String TEXT_132 = "(";
  protected final String TEXT_133 = "[])";
  protected final String TEXT_134 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_135 = " list = (";
  protected final String TEXT_136 = ")";
  protected final String TEXT_137 = ";";
  protected final String TEXT_138 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_139 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_140 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX2" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_141 = NL + "\t";
  protected final String TEXT_142 = " get";
  protected final String TEXT_143 = "(int index);" + NL;
  protected final String TEXT_144 = NL + "\tpublic ";
  protected final String TEXT_145 = " get";
  protected final String TEXT_146 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_147 = "(";
  protected final String TEXT_148 = ")";
  protected final String TEXT_149 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_150 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX3" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_151 = NL + "\tint get";
  protected final String TEXT_152 = "Length();" + NL;
  protected final String TEXT_153 = NL + "\tpublic int get";
  protected final String TEXT_154 = "Length()" + NL + "\t{";
  protected final String TEXT_155 = NL + "\t\treturn ";
  protected final String TEXT_156 = "().size();";
  protected final String TEXT_157 = NL + "\t\treturn ";
  protected final String TEXT_158 = " == null ? 0 : ";
  protected final String TEXT_159 = ".size();";
  protected final String TEXT_160 = NL + "\t}" + NL;
  protected final String TEXT_161 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX4" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_162 = NL + "\tvoid set";
  protected final String TEXT_163 = "(";
  protected final String TEXT_164 = "[] new";
  protected final String TEXT_165 = ");" + NL;
  protected final String TEXT_166 = NL + "\tpublic void set";
  protected final String TEXT_167 = "(";
  protected final String TEXT_168 = "[] new";
  protected final String TEXT_169 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_170 = ")";
  protected final String TEXT_171 = "()).setData(new";
  protected final String TEXT_172 = ".length, new";
  protected final String TEXT_173 = ");" + NL + "\t}" + NL;
  protected final String TEXT_174 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX5" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_175 = NL + "\tvoid set";
  protected final String TEXT_176 = "(int index, ";
  protected final String TEXT_177 = " element);" + NL;
  protected final String TEXT_178 = NL + "\tpublic void set";
  protected final String TEXT_179 = "(int index, ";
  protected final String TEXT_180 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_181 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_182 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_183 = "</b></em>' ";
  protected final String TEXT_184 = ".";
  protected final String TEXT_185 = NL + "\t * The key is of type ";
  protected final String TEXT_186 = "list of {@link ";
  protected final String TEXT_187 = "}";
  protected final String TEXT_188 = "{@link ";
  protected final String TEXT_189 = "}";
  protected final String TEXT_190 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_191 = "list of {@link ";
  protected final String TEXT_192 = "}";
  protected final String TEXT_193 = "{@link ";
  protected final String TEXT_194 = "}";
  protected final String TEXT_195 = ",";
  protected final String TEXT_196 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_197 = "}";
  protected final String TEXT_198 = ".";
  protected final String TEXT_199 = NL + "\t * The default value is <code>";
  protected final String TEXT_200 = "</code>.";
  protected final String TEXT_201 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_202 = "}.";
  protected final String TEXT_203 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_204 = "#";
  protected final String TEXT_205 = " <em>";
  protected final String TEXT_206 = "</em>}'.";
  protected final String TEXT_207 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX6a";
  protected final String TEXT_208 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_209 = "</em>' ";
  protected final String TEXT_210 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_211 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_212 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t *XX6b" + NL + "\t * ";
  protected final String TEXT_213 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_214 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_215 = "</em>' ";
  protected final String TEXT_216 = ".";
  protected final String TEXT_217 = NL + "\t * @see ";
  protected final String TEXT_218 = NL + "\t * @see #isSet";
  protected final String TEXT_219 = "()";
  protected final String TEXT_220 = NL + "\t * @see #unset";
  protected final String TEXT_221 = "()";
  protected final String TEXT_222 = NL + "\t * @see #set";
  protected final String TEXT_223 = "(";
  protected final String TEXT_224 = ")";
  protected final String TEXT_225 = NL + "\t * @see ";
  protected final String TEXT_226 = "#get";
  protected final String TEXT_227 = "()";
  protected final String TEXT_228 = NL + "\t * @see ";
  protected final String TEXT_229 = "#";
  protected final String TEXT_230 = NL + "\t * @model ";
  protected final String TEXT_231 = NL + "\t *        ";
  protected final String TEXT_232 = NL + "\t * @model";
  protected final String TEXT_233 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_234 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX7" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_235 = NL + "\t";
  protected final String TEXT_236 = " ";
  protected final String TEXT_237 = "();";
  protected final String TEXT_238 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_239 = NL + "\tpublic ";
  protected final String TEXT_240 = " ";
  protected final String TEXT_241 = "_";
  protected final String TEXT_242 = "()" + NL + "\t{";
  protected final String TEXT_243 = NL + "\t\treturn ";
  protected final String TEXT_244 = "(";
  protected final String TEXT_245 = "(";
  protected final String TEXT_246 = ")eDynamicGet(";
  protected final String TEXT_247 = ", ";
  protected final String TEXT_248 = ", true, ";
  protected final String TEXT_249 = ")";
  protected final String TEXT_250 = ").";
  protected final String TEXT_251 = "()";
  protected final String TEXT_252 = ";";
  protected final String TEXT_253 = NL + "\t\treturn ";
  protected final String TEXT_254 = "(";
  protected final String TEXT_255 = "(";
  protected final String TEXT_256 = ")eGet(";
  protected final String TEXT_257 = ", true)";
  protected final String TEXT_258 = ").";
  protected final String TEXT_259 = "()";
  protected final String TEXT_260 = ";";
  protected final String TEXT_261 = NL + "\t\treturn ";
  protected final String TEXT_262 = "(";
  protected final String TEXT_263 = "(";
  protected final String TEXT_264 = ")";
  protected final String TEXT_265 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_266 = ").";
  protected final String TEXT_267 = "()";
  protected final String TEXT_268 = ";";
  protected final String TEXT_269 = "      " + NL + "\t\t";
  protected final String TEXT_270 = " ";
  protected final String TEXT_271 = " = (";
  protected final String TEXT_272 = ")eVirtualGet(";
  protected final String TEXT_273 = ");" + NL + "\t  ";
  protected final String TEXT_274 = "\t" + NL + "\t  ";
  protected final String TEXT_275 = " " + NL + "\t\t";
  protected final String TEXT_276 = NL + "\t\tif (";
  protected final String TEXT_277 = " == null){" + NL + "\t\t";
  protected final String TEXT_278 = NL + "\t\tif (";
  protected final String TEXT_279 = " == null){";
  protected final String TEXT_280 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_281 = ", ";
  protected final String TEXT_282 = " = new ";
  protected final String TEXT_283 = ");";
  protected final String TEXT_284 = " = new ";
  protected final String TEXT_285 = ";" + NL + "\t\t\tif (isLoaded()) " + NL + "\t\t\t((";
  protected final String TEXT_286 = ") this.eResource()).getOnDemand(this, ";
  protected final String TEXT_287 = ");\t\t\t";
  protected final String TEXT_288 = "}" + NL + "\t\treturn ";
  protected final String TEXT_289 = ";";
  protected final String TEXT_290 = NL + "\t\tif (isLoaded() && eContainer() == null) {" + NL + "\t\t\t";
  protected final String TEXT_291 = " ";
  protected final String TEXT_292 = " = (";
  protected final String TEXT_293 = ") ((";
  protected final String TEXT_294 = ") this.eResource()).getContainerOnDemand(this, ";
  protected final String TEXT_295 = ");" + NL + "\t\t\tbasicSet";
  protected final String TEXT_296 = "(";
  protected final String TEXT_297 = ",null);}" + NL + "\t\treturn (";
  protected final String TEXT_298 = ")eContainer();";
  protected final String TEXT_299 = NL + "\t\t";
  protected final String TEXT_300 = " ";
  protected final String TEXT_301 = " = (";
  protected final String TEXT_302 = ")eVirtualGet(";
  protected final String TEXT_303 = ", Data";
  protected final String TEXT_304 = ".";
  protected final String TEXT_305 = ");";
  protected final String TEXT_306 = NL + "\t\tif (";
  protected final String TEXT_307 = " == null && isLoaded())" + NL + "\t\t{" + NL + "\t\t\t((";
  protected final String TEXT_308 = ") this.eResource()).getOnDemand(this, ";
  protected final String TEXT_309 = ");" + NL + "\t\t}";
  protected final String TEXT_310 = "\t\t";
  protected final String TEXT_311 = NL + "\t\tif ( isLoaded()) " + NL + "\t\t\teNotify(new ENotificationImpl(this, ";
  protected final String TEXT_312 = ".GET, ";
  protected final String TEXT_313 = ", null, null));" + NL + "\t\treturn (";
  protected final String TEXT_314 = ")eVirtualGet(";
  protected final String TEXT_315 = ", Data";
  protected final String TEXT_316 = ".";
  protected final String TEXT_317 = ");";
  protected final String TEXT_318 = NL + "\t\t  if ( isLoaded()) " + NL + "\t\t\teNotify(new ENotificationImpl(this, ";
  protected final String TEXT_319 = ".GET, ";
  protected final String TEXT_320 = ", null, null));" + NL + "\t\treturn (";
  protected final String TEXT_321 = " & Data";
  protected final String TEXT_322 = ".";
  protected final String TEXT_323 = "_EFLAG) != 0;";
  protected final String TEXT_324 = NL + "\t\tif ( isLoaded()) " + NL + "\t\t\teNotify(new ENotificationImpl(this, ";
  protected final String TEXT_325 = ".GET, ";
  protected final String TEXT_326 = ", null, null));" + NL + "\t\treturn Data";
  protected final String TEXT_327 = ".";
  protected final String TEXT_328 = "_EFLAG_VALUES[(";
  protected final String TEXT_329 = " & Data";
  protected final String TEXT_330 = ".";
  protected final String TEXT_331 = "_EFLAG) >>> Data";
  protected final String TEXT_332 = ".";
  protected final String TEXT_333 = "_EFLAG_OFFSET];";
  protected final String TEXT_334 = NL + "\t\tif ( isLoaded()) " + NL + "\t\t\teNotify(new ENotificationImpl(this, ";
  protected final String TEXT_335 = ".GET, ";
  protected final String TEXT_336 = ", null, null));" + NL + "\t\treturn ";
  protected final String TEXT_337 = ";";
  protected final String TEXT_338 = NL + "\t\t";
  protected final String TEXT_339 = " ";
  protected final String TEXT_340 = " = basicGet";
  protected final String TEXT_341 = "();" + NL + "\t\treturn ";
  protected final String TEXT_342 = " != null && ";
  protected final String TEXT_343 = ".eIsProxy() ? ";
  protected final String TEXT_344 = "eResolveProxy((";
  protected final String TEXT_345 = ")";
  protected final String TEXT_346 = ") : ";
  protected final String TEXT_347 = ";";
  protected final String TEXT_348 = NL + "\t\treturn new ";
  protected final String TEXT_349 = "((";
  protected final String TEXT_350 = ".Internal)((";
  protected final String TEXT_351 = ".Internal.Wrapper)get";
  protected final String TEXT_352 = "()).featureMap().";
  protected final String TEXT_353 = "list(";
  protected final String TEXT_354 = "));";
  protected final String TEXT_355 = NL + "\t\treturn (";
  protected final String TEXT_356 = ")get";
  protected final String TEXT_357 = "().";
  protected final String TEXT_358 = "list(";
  protected final String TEXT_359 = ");";
  protected final String TEXT_360 = NL + "\t\treturn ((";
  protected final String TEXT_361 = ".Internal.Wrapper)get";
  protected final String TEXT_362 = "()).featureMap().list(";
  protected final String TEXT_363 = ");";
  protected final String TEXT_364 = NL + "\t\treturn get";
  protected final String TEXT_365 = "().list(";
  protected final String TEXT_366 = ");";
  protected final String TEXT_367 = NL + "\t\treturn ";
  protected final String TEXT_368 = "(";
  protected final String TEXT_369 = "(";
  protected final String TEXT_370 = ")";
  protected final String TEXT_371 = "((";
  protected final String TEXT_372 = ".Internal.Wrapper)get";
  protected final String TEXT_373 = "()).featureMap().get(";
  protected final String TEXT_374 = ", true)";
  protected final String TEXT_375 = ").";
  protected final String TEXT_376 = "()";
  protected final String TEXT_377 = ";";
  protected final String TEXT_378 = NL + "\t\treturn ";
  protected final String TEXT_379 = "(";
  protected final String TEXT_380 = "(";
  protected final String TEXT_381 = ")";
  protected final String TEXT_382 = "get";
  protected final String TEXT_383 = "().get(";
  protected final String TEXT_384 = ", true)";
  protected final String TEXT_385 = ").";
  protected final String TEXT_386 = "()";
  protected final String TEXT_387 = ";";
  protected final String TEXT_388 = NL + "\t\t";
  protected final String TEXT_389 = NL + "\t\t";
  protected final String TEXT_390 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_391 = "' ";
  protected final String TEXT_392 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_393 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_394 = "EcoreEMap";
  protected final String TEXT_395 = "BasicFeatureMap";
  protected final String TEXT_396 = "EcoreEList";
  protected final String TEXT_397 = " should be used.";
  protected final String TEXT_398 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_399 = "\t" + NL + "\t}";
  protected final String TEXT_400 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_401 = NL + "\tpublic ";
  protected final String TEXT_402 = " basicGet";
  protected final String TEXT_403 = "()" + NL + "\t{";
  protected final String TEXT_404 = NL + "\t\treturn (";
  protected final String TEXT_405 = ")eDynamicGet(";
  protected final String TEXT_406 = ", ";
  protected final String TEXT_407 = ", false, ";
  protected final String TEXT_408 = ");";
  protected final String TEXT_409 = NL + "\t\treturn ";
  protected final String TEXT_410 = "(";
  protected final String TEXT_411 = "(";
  protected final String TEXT_412 = ")";
  protected final String TEXT_413 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_414 = ").";
  protected final String TEXT_415 = "()";
  protected final String TEXT_416 = ";";
  protected final String TEXT_417 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_418 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_419 = ")eInternalContainer();";
  protected final String TEXT_420 = NL + "\t\treturn (";
  protected final String TEXT_421 = ")eVirtualGet(";
  protected final String TEXT_422 = ");";
  protected final String TEXT_423 = NL + "\t\treturn data != null ? ";
  protected final String TEXT_424 = " : null;";
  protected final String TEXT_425 = NL + "\t\treturn (";
  protected final String TEXT_426 = ")((";
  protected final String TEXT_427 = ".Internal.Wrapper)get";
  protected final String TEXT_428 = "()).featureMap().get(";
  protected final String TEXT_429 = ", false);";
  protected final String TEXT_430 = NL + "\t\treturn (";
  protected final String TEXT_431 = ")get";
  protected final String TEXT_432 = "().get(";
  protected final String TEXT_433 = ", false);";
  protected final String TEXT_434 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_435 = "' ";
  protected final String TEXT_436 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_437 = NL + "\t}" + NL;
  protected final String TEXT_438 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *XX9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_439 = NL + "\tpublic ";
  protected final String TEXT_440 = " basicSet";
  protected final String TEXT_441 = "(";
  protected final String TEXT_442 = " new";
  protected final String TEXT_443 = ", ";
  protected final String TEXT_444 = " msgs)" + NL + "\t{" + NL + "\t";
  protected final String TEXT_445 = NL + "\t\t" + NL + "\t";
  protected final String TEXT_446 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_447 = ")new";
  protected final String TEXT_448 = ", ";
  protected final String TEXT_449 = ", msgs);";
  protected final String TEXT_450 = NL + "\t\treturn msgs;";
  protected final String TEXT_451 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_452 = ")new";
  protected final String TEXT_453 = ", ";
  protected final String TEXT_454 = ", msgs);";
  protected final String TEXT_455 = NL + "\t\treturn msgs;";
  protected final String TEXT_456 = NL + "\t\tObject old";
  protected final String TEXT_457 = " = eVirtualSet(";
  protected final String TEXT_458 = ", new";
  protected final String TEXT_459 = ");";
  protected final String TEXT_460 = NL + "\t\t";
  protected final String TEXT_461 = " old";
  protected final String TEXT_462 = " = ";
  protected final String TEXT_463 = ";" + NL + "\t\t";
  protected final String TEXT_464 = " = new";
  protected final String TEXT_465 = ";";
  protected final String TEXT_466 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_467 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_468 = NL + "\t\tboolean old";
  protected final String TEXT_469 = "ESet = (";
  protected final String TEXT_470 = " & ";
  protected final String TEXT_471 = "_ESETFLAG) != 0;";
  protected final String TEXT_472 = NL + "\t\t";
  protected final String TEXT_473 = " |= ";
  protected final String TEXT_474 = "_ESETFLAG;";
  protected final String TEXT_475 = NL + "\t\tboolean old";
  protected final String TEXT_476 = "ESet = ";
  protected final String TEXT_477 = "ESet;";
  protected final String TEXT_478 = NL + "\t\t";
  protected final String TEXT_479 = "ESet = true;";
  protected final String TEXT_480 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_481 = NL + "\t\t\t";
  protected final String TEXT_482 = " notification = new ";
  protected final String TEXT_483 = "(this, ";
  protected final String TEXT_484 = ".SET, ";
  protected final String TEXT_485 = ", ";
  protected final String TEXT_486 = "isSetChange ? null : old";
  protected final String TEXT_487 = "old";
  protected final String TEXT_488 = ", new";
  protected final String TEXT_489 = ", ";
  protected final String TEXT_490 = "isSetChange";
  protected final String TEXT_491 = "!old";
  protected final String TEXT_492 = "ESet";
  protected final String TEXT_493 = ");";
  protected final String TEXT_494 = NL + "\t\t\t";
  protected final String TEXT_495 = " notification = new ";
  protected final String TEXT_496 = "(this, ";
  protected final String TEXT_497 = ".SET, ";
  protected final String TEXT_498 = ", ";
  protected final String TEXT_499 = "old";
  protected final String TEXT_500 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_501 = "old";
  protected final String TEXT_502 = ", new";
  protected final String TEXT_503 = ");";
  protected final String TEXT_504 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_505 = NL + "\t\treturn msgs;";
  protected final String TEXT_506 = NL + "\t\treturn ((";
  protected final String TEXT_507 = ".Internal)((";
  protected final String TEXT_508 = ".Internal.Wrapper)get";
  protected final String TEXT_509 = "()).featureMap()).basicAdd(";
  protected final String TEXT_510 = ", new";
  protected final String TEXT_511 = ", msgs);";
  protected final String TEXT_512 = NL + "\t\treturn ((";
  protected final String TEXT_513 = ".Internal)get";
  protected final String TEXT_514 = "()).basicAdd(";
  protected final String TEXT_515 = ", new";
  protected final String TEXT_516 = ", msgs);";
  protected final String TEXT_517 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_518 = "' ";
  protected final String TEXT_519 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_520 = NL + "\t}" + NL;
  protected final String TEXT_521 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_522 = "#";
  protected final String TEXT_523 = " <em>";
  protected final String TEXT_524 = "</em>}' ";
  protected final String TEXT_525 = ".";
  protected final String TEXT_526 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY1" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_527 = "</em>' ";
  protected final String TEXT_528 = ".";
  protected final String TEXT_529 = NL + "\t * @see ";
  protected final String TEXT_530 = NL + "\t * @see #isSet";
  protected final String TEXT_531 = "()";
  protected final String TEXT_532 = NL + "\t * @see #unset";
  protected final String TEXT_533 = "()";
  protected final String TEXT_534 = NL + "\t * @see #";
  protected final String TEXT_535 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_536 = NL + " /**" + NL + " * <!-- begin-user-doc -->" + NL + " *YY2" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */";
  protected final String TEXT_537 = NL + "\tvoid set";
  protected final String TEXT_538 = "(";
  protected final String TEXT_539 = " value);" + NL;
  protected final String TEXT_540 = NL + "\tpublic void set";
  protected final String TEXT_541 = "_";
  protected final String TEXT_542 = "(";
  protected final String TEXT_543 = " ";
  protected final String TEXT_544 = ")" + NL + "\t{";
  protected final String TEXT_545 = NL + "\t";
  protected final String TEXT_546 = NL + "\t\t";
  protected final String TEXT_547 = NL + "\t";
  protected final String TEXT_548 = NL + "\t\teDynamicSet(";
  protected final String TEXT_549 = ", ";
  protected final String TEXT_550 = ", ";
  protected final String TEXT_551 = "new ";
  protected final String TEXT_552 = "(";
  protected final String TEXT_553 = "new";
  protected final String TEXT_554 = ")";
  protected final String TEXT_555 = ");";
  protected final String TEXT_556 = NL + "\t\teSet(";
  protected final String TEXT_557 = ", ";
  protected final String TEXT_558 = "new ";
  protected final String TEXT_559 = "(";
  protected final String TEXT_560 = "new";
  protected final String TEXT_561 = ")";
  protected final String TEXT_562 = ");";
  protected final String TEXT_563 = NL + "\t\t";
  protected final String TEXT_564 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_565 = "new ";
  protected final String TEXT_566 = "(";
  protected final String TEXT_567 = "new";
  protected final String TEXT_568 = ")";
  protected final String TEXT_569 = ");";
  protected final String TEXT_570 = NL + "\t\tif (new";
  protected final String TEXT_571 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_572 = " && new";
  protected final String TEXT_573 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_574 = ".isAncestor(this, ";
  protected final String TEXT_575 = "new";
  protected final String TEXT_576 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_577 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_578 = NL + "\t\t\t";
  protected final String TEXT_579 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_580 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_581 = ")new";
  protected final String TEXT_582 = ").eInverseAdd(this, ";
  protected final String TEXT_583 = ", ";
  protected final String TEXT_584 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_585 = "(";
  protected final String TEXT_586 = "new";
  protected final String TEXT_587 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_588 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_589 = "(this, ";
  protected final String TEXT_590 = ".SET, ";
  protected final String TEXT_591 = ", new";
  protected final String TEXT_592 = ", new";
  protected final String TEXT_593 = "));";
  protected final String TEXT_594 = NL + "\t\t";
  protected final String TEXT_595 = " ";
  protected final String TEXT_596 = " = (";
  protected final String TEXT_597 = ")eVirtualGet(";
  protected final String TEXT_598 = ");";
  protected final String TEXT_599 = NL + "\t\tif (new";
  protected final String TEXT_600 = " != ";
  protected final String TEXT_601 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_602 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_603 = " != null)";
  protected final String TEXT_604 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_605 = ") ";
  protected final String TEXT_606 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_607 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_608 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_609 = ")new";
  protected final String TEXT_610 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_611 = ", null, msgs);";
  protected final String TEXT_612 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_613 = ") ";
  protected final String TEXT_614 = ").eInverseRemove(this, ";
  protected final String TEXT_615 = ", ";
  protected final String TEXT_616 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_617 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_618 = ")new";
  protected final String TEXT_619 = ").eInverseAdd(this, ";
  protected final String TEXT_620 = ", ";
  protected final String TEXT_621 = ".class, msgs);";
  protected final String TEXT_622 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_623 = "(";
  protected final String TEXT_624 = "new";
  protected final String TEXT_625 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_626 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_627 = NL + "\t\t\tboolean old";
  protected final String TEXT_628 = "ESet = eVirtualIsSet(";
  protected final String TEXT_629 = ");";
  protected final String TEXT_630 = NL + "\t\t\tboolean old";
  protected final String TEXT_631 = "ESet = (";
  protected final String TEXT_632 = " & ";
  protected final String TEXT_633 = "_ESETFLAG) != 0;";
  protected final String TEXT_634 = NL + "\t\t\t";
  protected final String TEXT_635 = " |= ";
  protected final String TEXT_636 = "_ESETFLAG;";
  protected final String TEXT_637 = NL + "\t\t\tboolean old";
  protected final String TEXT_638 = "ESet = ";
  protected final String TEXT_639 = "ESet;";
  protected final String TEXT_640 = NL + "\t\t\t";
  protected final String TEXT_641 = "ESet = true;";
  protected final String TEXT_642 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_643 = "(this, ";
  protected final String TEXT_644 = ".SET, ";
  protected final String TEXT_645 = ", new";
  protected final String TEXT_646 = ", new";
  protected final String TEXT_647 = ", !old";
  protected final String TEXT_648 = "ESet));";
  protected final String TEXT_649 = NL + "\t\t}";
  protected final String TEXT_650 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_651 = "(this, ";
  protected final String TEXT_652 = ".SET, ";
  protected final String TEXT_653 = ", new";
  protected final String TEXT_654 = ", new";
  protected final String TEXT_655 = "));";
  protected final String TEXT_656 = NL + "\t\t";
  protected final String TEXT_657 = " old";
  protected final String TEXT_658 = " = (";
  protected final String TEXT_659 = " & Data";
  protected final String TEXT_660 = ".";
  protected final String TEXT_661 = "_EFLAG) != 0;";
  protected final String TEXT_662 = NL + "\t\t";
  protected final String TEXT_663 = " old";
  protected final String TEXT_664 = " = ";
  protected final String TEXT_665 = "_EFLAG_VALUES[(";
  protected final String TEXT_666 = " & Data";
  protected final String TEXT_667 = ".";
  protected final String TEXT_668 = "_EFLAG) >>> ";
  protected final String TEXT_669 = "_EFLAG_OFFSET];";
  protected final String TEXT_670 = NL + "\t\tif (new";
  protected final String TEXT_671 = ") ";
  protected final String TEXT_672 = " |= Data";
  protected final String TEXT_673 = ".";
  protected final String TEXT_674 = "_EFLAG; else ";
  protected final String TEXT_675 = " &= Data";
  protected final String TEXT_676 = ".";
  protected final String TEXT_677 = "_EFLAG;";
  protected final String TEXT_678 = NL + "\t\tif (new";
  protected final String TEXT_679 = " == null) new";
  protected final String TEXT_680 = " = ";
  protected final String TEXT_681 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_682 = " = ";
  protected final String TEXT_683 = " & Data";
  protected final String TEXT_684 = ".";
  protected final String TEXT_685 = "_EFLAG | ";
  protected final String TEXT_686 = "new";
  protected final String TEXT_687 = ".ordinal()";
  protected final String TEXT_688 = ".VALUES.indexOf(new";
  protected final String TEXT_689 = ")";
  protected final String TEXT_690 = " << ";
  protected final String TEXT_691 = "_EFLAG_OFFSET;";
  protected final String TEXT_692 = NL + "\t\t";
  protected final String TEXT_693 = " old";
  protected final String TEXT_694 = " = ";
  protected final String TEXT_695 = ";";
  protected final String TEXT_696 = NL + "\t\t";
  protected final String TEXT_697 = " ";
  protected final String TEXT_698 = " = new";
  protected final String TEXT_699 = " == null ? Data";
  protected final String TEXT_700 = ".";
  protected final String TEXT_701 = " : new";
  protected final String TEXT_702 = ";";
  protected final String TEXT_703 = NL + "\t\t";
  protected final String TEXT_704 = " = new";
  protected final String TEXT_705 = " == null ? ";
  protected final String TEXT_706 = " : new";
  protected final String TEXT_707 = ";";
  protected final String TEXT_708 = NL + "\t\t";
  protected final String TEXT_709 = " ";
  protected final String TEXT_710 = " = ";
  protected final String TEXT_711 = "new";
  protected final String TEXT_712 = ";";
  protected final String TEXT_713 = NL + "\t\t";
  protected final String TEXT_714 = " = ";
  protected final String TEXT_715 = "new";
  protected final String TEXT_716 = ";";
  protected final String TEXT_717 = NL + "\t\tObject old";
  protected final String TEXT_718 = " = eVirtualSet(";
  protected final String TEXT_719 = ", ";
  protected final String TEXT_720 = ");";
  protected final String TEXT_721 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_722 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_723 = NL + "\t\tboolean old";
  protected final String TEXT_724 = "ESet = (";
  protected final String TEXT_725 = " & ";
  protected final String TEXT_726 = "_ESETFLAG) != 0;";
  protected final String TEXT_727 = NL + "\t\t";
  protected final String TEXT_728 = " |= ";
  protected final String TEXT_729 = "_ESETFLAG;";
  protected final String TEXT_730 = NL + "\t\tboolean old";
  protected final String TEXT_731 = "ESet = ";
  protected final String TEXT_732 = "ESet;";
  protected final String TEXT_733 = NL + "\t\t";
  protected final String TEXT_734 = "ESet = true;";
  protected final String TEXT_735 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_736 = NL + "\t\t\t(this, ";
  protected final String TEXT_737 = ".SET," + NL + "\t\t\t";
  protected final String TEXT_738 = ", " + NL + "\t\t\t";
  protected final String TEXT_739 = "isSetChange ? Data";
  protected final String TEXT_740 = ".";
  protected final String TEXT_741 = " : old";
  protected final String TEXT_742 = "old";
  protected final String TEXT_743 = ", ";
  protected final String TEXT_744 = "new";
  protected final String TEXT_745 = ", ";
  protected final String TEXT_746 = "isSetChange";
  protected final String TEXT_747 = "!old";
  protected final String TEXT_748 = "ESet";
  protected final String TEXT_749 = "));";
  protected final String TEXT_750 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_751 = "(" + NL + "\t\t\tthis, ";
  protected final String TEXT_752 = ".SET," + NL + "\t\t\t";
  protected final String TEXT_753 = "," + NL + "\t\t\t";
  protected final String TEXT_754 = NL + "\t\t\t\told";
  protected final String TEXT_755 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_756 = " : old";
  protected final String TEXT_757 = "old";
  protected final String TEXT_758 = ", ";
  protected final String TEXT_759 = "new";
  protected final String TEXT_760 = "));";
  protected final String TEXT_761 = NL + "\t\t((";
  protected final String TEXT_762 = ".Internal)((";
  protected final String TEXT_763 = ".Internal.Wrapper)get";
  protected final String TEXT_764 = "()).featureMap()).set(";
  protected final String TEXT_765 = ", ";
  protected final String TEXT_766 = "new ";
  protected final String TEXT_767 = "(";
  protected final String TEXT_768 = "new";
  protected final String TEXT_769 = ")";
  protected final String TEXT_770 = ");";
  protected final String TEXT_771 = NL + "\t\t((";
  protected final String TEXT_772 = ".Internal)get";
  protected final String TEXT_773 = "()).set(";
  protected final String TEXT_774 = ", ";
  protected final String TEXT_775 = "new ";
  protected final String TEXT_776 = "(";
  protected final String TEXT_777 = "new";
  protected final String TEXT_778 = ")";
  protected final String TEXT_779 = ");";
  protected final String TEXT_780 = NL + "\t\t";
  protected final String TEXT_781 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_782 = "' ";
  protected final String TEXT_783 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_784 = NL + "\t}";
  protected final String TEXT_785 = " ";
  protected final String TEXT_786 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY3" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_787 = NL + "\tpublic ";
  protected final String TEXT_788 = " basicUnset";
  protected final String TEXT_789 = "(";
  protected final String TEXT_790 = " msgs)" + NL + "\t{";
  protected final String TEXT_791 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_792 = ")";
  protected final String TEXT_793 = "basicGet";
  protected final String TEXT_794 = "(), ";
  protected final String TEXT_795 = ", msgs);";
  protected final String TEXT_796 = "Object old";
  protected final String TEXT_797 = " = ";
  protected final String TEXT_798 = "eVirtualUnset(";
  protected final String TEXT_799 = ");";
  protected final String TEXT_800 = NL + "\t\t";
  protected final String TEXT_801 = " old";
  protected final String TEXT_802 = " = ";
  protected final String TEXT_803 = ";";
  protected final String TEXT_804 = NL + "\t\t";
  protected final String TEXT_805 = " = null;";
  protected final String TEXT_806 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_807 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_808 = NL + "\t\tboolean old";
  protected final String TEXT_809 = "ESet = (";
  protected final String TEXT_810 = " & ";
  protected final String TEXT_811 = "_ESETFLAG) != 0;";
  protected final String TEXT_812 = NL + "\t\t";
  protected final String TEXT_813 = " &= ~";
  protected final String TEXT_814 = "_ESETFLAG;";
  protected final String TEXT_815 = NL + "\t\tboolean old";
  protected final String TEXT_816 = "ESet = ";
  protected final String TEXT_817 = "ESet;";
  protected final String TEXT_818 = NL + "\t\t";
  protected final String TEXT_819 = "ESet = false;";
  protected final String TEXT_820 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_821 = " notification = new ";
  protected final String TEXT_822 = "(this, ";
  protected final String TEXT_823 = ".UNSET, ";
  protected final String TEXT_824 = ", ";
  protected final String TEXT_825 = "isSetChange ? old";
  protected final String TEXT_826 = " : null";
  protected final String TEXT_827 = "old";
  protected final String TEXT_828 = ", null, ";
  protected final String TEXT_829 = "isSetChange";
  protected final String TEXT_830 = "old";
  protected final String TEXT_831 = "ESet";
  protected final String TEXT_832 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_833 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_834 = "' ";
  protected final String TEXT_835 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_836 = NL + "\t}" + NL;
  protected final String TEXT_837 = NL;
  protected final String TEXT_838 = NL;
  protected final String TEXT_839 = "  ";
  protected final String TEXT_840 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_841 = "(";
  protected final String TEXT_842 = ") <em>";
  protected final String TEXT_843 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY8" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_844 = "(";
  protected final String TEXT_845 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_846 = " ";
  protected final String TEXT_847 = "__EEXPRESSION = \"";
  protected final String TEXT_848 = "\";";
  protected final String TEXT_849 = NL;
  protected final String TEXT_850 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_851 = "(";
  protected final String TEXT_852 = ") <em>";
  protected final String TEXT_853 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY9" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_854 = "(";
  protected final String TEXT_855 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_856 = ".Internal.InvocationDelegate ";
  protected final String TEXT_857 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_858 = ".Internal)";
  protected final String TEXT_859 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_860 = NL + "\t/**";
  protected final String TEXT_861 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY10" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_862 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_863 = NL + "\t * ";
  protected final String TEXT_864 = NL + "\t * @param ";
  protected final String TEXT_865 = NL + "\t *   ";
  protected final String TEXT_866 = NL + "\t * @param ";
  protected final String TEXT_867 = " ";
  protected final String TEXT_868 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_869 = NL + "\t * @model ";
  protected final String TEXT_870 = NL + "\t *        ";
  protected final String TEXT_871 = NL + "\t * @model";
  protected final String TEXT_872 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_873 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY11" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_874 = NL + "\t";
  protected final String TEXT_875 = " ";
  protected final String TEXT_876 = "(";
  protected final String TEXT_877 = ")";
  protected final String TEXT_878 = ";" + NL;
  protected final String TEXT_879 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_880 = NL + "\tpublic ";
  protected final String TEXT_881 = " ";
  protected final String TEXT_882 = "(";
  protected final String TEXT_883 = ")";
  protected final String TEXT_884 = NL + "\t{";
  protected final String TEXT_885 = NL + "\t\t";
  protected final String TEXT_886 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_887 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_888 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_889 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_890 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_891 = "\",";
  protected final String TEXT_892 = NL + "\t\t\t\t ";
  protected final String TEXT_893 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_894 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_895 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_896 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_897 = ".";
  protected final String TEXT_898 = ");";
  protected final String TEXT_899 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_900 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_901 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_902 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_903 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_904 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_905 = ".";
  protected final String TEXT_906 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_907 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_908 = "\", ";
  protected final String TEXT_909 = ".getObjectLabel(this, ";
  protected final String TEXT_910 = ") }),";
  protected final String TEXT_911 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_912 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_913 = NL + "\t\t\t";
  protected final String TEXT_914 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_915 = "new ";
  protected final String TEXT_916 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_917 = ", ";
  protected final String TEXT_918 = ")";
  protected final String TEXT_919 = "null";
  protected final String TEXT_920 = ");";
  protected final String TEXT_921 = NL + "\t\t\treturn ";
  protected final String TEXT_922 = "(";
  protected final String TEXT_923 = "(";
  protected final String TEXT_924 = ")";
  protected final String TEXT_925 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_926 = "new ";
  protected final String TEXT_927 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_928 = ", ";
  protected final String TEXT_929 = ")";
  protected final String TEXT_930 = "null";
  protected final String TEXT_931 = ")";
  protected final String TEXT_932 = ").";
  protected final String TEXT_933 = "()";
  protected final String TEXT_934 = ";";
  protected final String TEXT_935 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_936 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_937 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_938 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_939 = NL + "\t}" + NL;
  protected final String TEXT_940 = NL + "/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY12" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_941 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_942 = NL + "\t@Override";
  protected final String TEXT_943 = NL + "\tpublic ";
  protected final String TEXT_944 = " eInverseAdd(";
  protected final String TEXT_945 = " otherEnd, int featureID, ";
  protected final String TEXT_946 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_947 = ")" + NL + "\t\t{";
  protected final String TEXT_948 = NL + "\t\t\tcase ";
  protected final String TEXT_949 = ":";
  protected final String TEXT_950 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_951 = "(";
  protected final String TEXT_952 = ".InternalMapView";
  protected final String TEXT_953 = ")";
  protected final String TEXT_954 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_955 = NL + "\t\t\t\treturn (";
  protected final String TEXT_956 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_957 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_958 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_959 = "((";
  protected final String TEXT_960 = ")otherEnd, msgs);";
  protected final String TEXT_961 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_962 = ", msgs);";
  protected final String TEXT_963 = NL + "\t\t\t\t";
  protected final String TEXT_964 = " ";
  protected final String TEXT_965 = " = (";
  protected final String TEXT_966 = ")eVirtualGet(";
  protected final String TEXT_967 = ");";
  protected final String TEXT_968 = NL + "\t\t\t\t";
  protected final String TEXT_969 = " ";
  protected final String TEXT_970 = " = ";
  protected final String TEXT_971 = "basicGet";
  protected final String TEXT_972 = "();";
  protected final String TEXT_973 = NL + "\t\t\t\tif (";
  protected final String TEXT_974 = " != null)";
  protected final String TEXT_975 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_976 = ")";
  protected final String TEXT_977 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_978 = ", null, msgs);";
  protected final String TEXT_979 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_980 = ")";
  protected final String TEXT_981 = ").eInverseRemove(this, ";
  protected final String TEXT_982 = ", ";
  protected final String TEXT_983 = ".class, msgs);";
  protected final String TEXT_984 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_985 = "((";
  protected final String TEXT_986 = ")otherEnd, msgs);";
  protected final String TEXT_987 = NL + "\t\t}";
  protected final String TEXT_988 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_989 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_990 = NL + "\t}" + NL;
  protected final String TEXT_991 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY13" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_992 = NL + "\t@Override";
  protected final String TEXT_993 = NL + "\tpublic ";
  protected final String TEXT_994 = " eInverseRemove(";
  protected final String TEXT_995 = " otherEnd, int featureID, ";
  protected final String TEXT_996 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_997 = ")" + NL + "\t\t{";
  protected final String TEXT_998 = NL + "\t\t\tcase ";
  protected final String TEXT_999 = ":";
  protected final String TEXT_1000 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1001 = ")((";
  protected final String TEXT_1002 = ".InternalMapView";
  protected final String TEXT_1003 = ")";
  protected final String TEXT_1004 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1005 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1006 = ")((";
  protected final String TEXT_1007 = ".Internal.Wrapper)";
  protected final String TEXT_1008 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1009 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1010 = ")";
  protected final String TEXT_1011 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1012 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1013 = ", msgs);";
  protected final String TEXT_1014 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1015 = "(msgs);";
  protected final String TEXT_1016 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1017 = "(null, msgs);";
  protected final String TEXT_1018 = NL + "\t\t}";
  protected final String TEXT_1019 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1020 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1021 = NL + "\t}" + NL;
  protected final String TEXT_1022 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY14" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1023 = NL + "\t@Override";
  protected final String TEXT_1024 = NL + "\tpublic ";
  protected final String TEXT_1025 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1026 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1027 = ")" + NL + "\t\t{";
  protected final String TEXT_1028 = NL + "\t\t\tcase ";
  protected final String TEXT_1029 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1030 = ", ";
  protected final String TEXT_1031 = ".class, msgs);";
  protected final String TEXT_1032 = NL + "\t\t}";
  protected final String TEXT_1033 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1034 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1035 = NL + "\t}" + NL;
  protected final String TEXT_1036 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY15" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1037 = NL + "\t@Override";
  protected final String TEXT_1038 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1039 = ")" + NL + "\t\t{";
  protected final String TEXT_1040 = NL + "\t\t\tcase ";
  protected final String TEXT_1041 = ":";
  protected final String TEXT_1042 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1043 = "();";
  protected final String TEXT_1044 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1045 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1046 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1047 = "(";
  protected final String TEXT_1048 = "());";
  protected final String TEXT_1049 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1050 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1051 = "();";
  protected final String TEXT_1052 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1053 = ".InternalMapView";
  protected final String TEXT_1054 = ")";
  protected final String TEXT_1055 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1056 = "();";
  protected final String TEXT_1057 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1058 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1059 = "().map();";
  protected final String TEXT_1060 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1061 = ".Internal.Wrapper)";
  protected final String TEXT_1062 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1063 = "();";
  protected final String TEXT_1064 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1065 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1066 = ".Internal)";
  protected final String TEXT_1067 = "()).getWrapper();";
  protected final String TEXT_1068 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1069 = "();";
  protected final String TEXT_1070 = NL + "\t\t}";
  protected final String TEXT_1071 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1072 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1073 = NL + "\t}" + NL;
  protected final String TEXT_1074 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY16" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1075 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1076 = NL + "\t@Override";
  protected final String TEXT_1077 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1078 = ")" + NL + "\t\t{";
  protected final String TEXT_1079 = NL + "\t\t\tcase ";
  protected final String TEXT_1080 = ":";
  protected final String TEXT_1081 = NL + "\t\t\t\t((";
  protected final String TEXT_1082 = ".Internal)((";
  protected final String TEXT_1083 = ".Internal.Wrapper)";
  protected final String TEXT_1084 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1085 = NL + "\t\t\t\t((";
  protected final String TEXT_1086 = ".Internal)";
  protected final String TEXT_1087 = "()).set(newValue);";
  protected final String TEXT_1088 = NL + "\t\t\t\t((";
  protected final String TEXT_1089 = ".Setting)((";
  protected final String TEXT_1090 = ".InternalMapView";
  protected final String TEXT_1091 = ")";
  protected final String TEXT_1092 = "()).eMap()).set(newValue);";
  protected final String TEXT_1093 = NL + "\t\t\t\t((";
  protected final String TEXT_1094 = ".Setting)";
  protected final String TEXT_1095 = "()).set(newValue);";
  protected final String TEXT_1096 = NL + "\t\t\t\t";
  protected final String TEXT_1097 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1098 = "().addAll((";
  protected final String TEXT_1099 = "<? extends ";
  protected final String TEXT_1100 = ">";
  protected final String TEXT_1101 = ")newValue);";
  protected final String TEXT_1102 = NL + "\t\t\t\tset";
  protected final String TEXT_1103 = "(((";
  protected final String TEXT_1104 = ")newValue).";
  protected final String TEXT_1105 = "());";
  protected final String TEXT_1106 = NL + "\t\t\t\tset";
  protected final String TEXT_1107 = "(";
  protected final String TEXT_1108 = "(";
  protected final String TEXT_1109 = ")";
  protected final String TEXT_1110 = "newValue);";
  protected final String TEXT_1111 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1112 = NL + "\t\t}";
  protected final String TEXT_1113 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1114 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1115 = NL + "\t}" + NL;
  protected final String TEXT_1116 = NL;
  protected final String TEXT_1117 = "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY17-Bis" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1118 = NL + "\t@Override";
  protected final String TEXT_1119 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1120 = ")" + NL + "\t\t{";
  protected final String TEXT_1121 = NL + "\t\t\tcase ";
  protected final String TEXT_1122 = ":";
  protected final String TEXT_1123 = NL + "\t\t\t\t((";
  protected final String TEXT_1124 = ".Internal.Wrapper)";
  protected final String TEXT_1125 = "()).featureMap().clear();";
  protected final String TEXT_1126 = NL + "\t\t\t\t";
  protected final String TEXT_1127 = "().clear();";
  protected final String TEXT_1128 = NL + "\t\t\t\tunset";
  protected final String TEXT_1129 = "();";
  protected final String TEXT_1130 = NL + "\t\t\t\tset";
  protected final String TEXT_1131 = "((";
  protected final String TEXT_1132 = ")null);";
  protected final String TEXT_1133 = NL + "\t\t\t\t";
  protected final String TEXT_1134 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1135 = NL + "\t\t\t\tset";
  protected final String TEXT_1136 = "(Data";
  protected final String TEXT_1137 = ".";
  protected final String TEXT_1138 = ");";
  protected final String TEXT_1139 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1140 = NL + "\t\t}";
  protected final String TEXT_1141 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1142 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1143 = NL + "\t}" + NL;
  protected final String TEXT_1144 = NL;
  protected final String TEXT_1145 = "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY18" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1146 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1147 = NL + "\t@Override";
  protected final String TEXT_1148 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1149 = ")" + NL + "\t\t{";
  protected final String TEXT_1150 = NL + "\t\t\tcase ";
  protected final String TEXT_1151 = ":";
  protected final String TEXT_1152 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1153 = "();";
  protected final String TEXT_1154 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1155 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1156 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1157 = ".Internal.Wrapper)";
  protected final String TEXT_1158 = "()).featureMap().isEmpty();";
  protected final String TEXT_1159 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1160 = "() != null && !";
  protected final String TEXT_1161 = "().featureMap().isEmpty();";
  protected final String TEXT_1162 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1163 = "() != null && !";
  protected final String TEXT_1164 = "().isEmpty();";
  protected final String TEXT_1165 = NL + "\t\t\t\t";
  protected final String TEXT_1166 = " ";
  protected final String TEXT_1167 = " = (";
  protected final String TEXT_1168 = ")eVirtualGet(";
  protected final String TEXT_1169 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1170 = " != null && !";
  protected final String TEXT_1171 = ".isEmpty();";
  protected final String TEXT_1172 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1173 = "().isEmpty();";
  protected final String TEXT_1174 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1175 = "();";
  protected final String TEXT_1176 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1177 = "() != null;";
  protected final String TEXT_1178 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1179 = ") != null;";
  protected final String TEXT_1180 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1181 = "() != null;";
  protected final String TEXT_1182 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1183 = "() != null;";
  protected final String TEXT_1184 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1185 = ") != null;";
  protected final String TEXT_1186 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1187 = "() != null;";
  protected final String TEXT_1188 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1189 = " & Data";
  protected final String TEXT_1190 = ".";
  protected final String TEXT_1191 = "_EFLAG) != 0) != Data";
  protected final String TEXT_1192 = ".";
  protected final String TEXT_1193 = ";";
  protected final String TEXT_1194 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1195 = " & Data";
  protected final String TEXT_1196 = ".";
  protected final String TEXT_1197 = "_EFLAG) != Data";
  protected final String TEXT_1198 = ".";
  protected final String TEXT_1199 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1200 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1201 = "() != Data";
  protected final String TEXT_1202 = ".";
  protected final String TEXT_1203 = ";";
  protected final String TEXT_1204 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1205 = ", Data";
  protected final String TEXT_1206 = ".";
  protected final String TEXT_1207 = ") != Data";
  protected final String TEXT_1208 = ".";
  protected final String TEXT_1209 = ";";
  protected final String TEXT_1210 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1211 = "() != Data";
  protected final String TEXT_1212 = ".";
  protected final String TEXT_1213 = ";";
  protected final String TEXT_1214 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1215 = ".";
  protected final String TEXT_1216 = " == null ? ";
  protected final String TEXT_1217 = "() != null : !Data";
  protected final String TEXT_1218 = ".";
  protected final String TEXT_1219 = ".equals(";
  protected final String TEXT_1220 = "());";
  protected final String TEXT_1221 = NL + "\t\t\t\t";
  protected final String TEXT_1222 = " ";
  protected final String TEXT_1223 = " = (";
  protected final String TEXT_1224 = ")eVirtualGet(";
  protected final String TEXT_1225 = ", Data";
  protected final String TEXT_1226 = ".";
  protected final String TEXT_1227 = ");" + NL + "\t\t\t\treturn Data";
  protected final String TEXT_1228 = ".";
  protected final String TEXT_1229 = " == null ? ";
  protected final String TEXT_1230 = "() != null : !Data";
  protected final String TEXT_1231 = ".";
  protected final String TEXT_1232 = ".equals(";
  protected final String TEXT_1233 = "());";
  protected final String TEXT_1234 = NL + "\t\t\t\treturn Data";
  protected final String TEXT_1235 = ".";
  protected final String TEXT_1236 = " == null ? ";
  protected final String TEXT_1237 = "() != null : !Data";
  protected final String TEXT_1238 = ".";
  protected final String TEXT_1239 = ".equals(";
  protected final String TEXT_1240 = "());";
  protected final String TEXT_1241 = NL + "\t\t}";
  protected final String TEXT_1242 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1243 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1244 = NL + "\t}";
  protected final String TEXT_1245 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY19" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1246 = NL + "\t@Override";
  protected final String TEXT_1247 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1248 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1249 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1250 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1251 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1252 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1253 = ": return ";
  protected final String TEXT_1254 = ";";
  protected final String TEXT_1255 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1256 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1257 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY20" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1258 = NL + "\t@Override";
  protected final String TEXT_1259 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1260 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1261 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1262 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1263 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1264 = ": return ";
  protected final String TEXT_1265 = ";";
  protected final String TEXT_1266 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1267 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1268 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1269 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1270 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1271 = ": return ";
  protected final String TEXT_1272 = ";";
  protected final String TEXT_1273 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1274 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1275 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY21" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1276 = NL + "\t@Override";
  protected final String TEXT_1277 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1278 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1279 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1280 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1281 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1282 = ": return ";
  protected final String TEXT_1283 = ";";
  protected final String TEXT_1284 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1285 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1286 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1287 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1288 = ": return ";
  protected final String TEXT_1289 = ";";
  protected final String TEXT_1290 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1291 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1292 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1293 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1294 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1295 = ": return ";
  protected final String TEXT_1296 = ";";
  protected final String TEXT_1297 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1298 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1299 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY22" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1300 = NL + "\t@Override";
  protected final String TEXT_1301 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1302 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY23" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1303 = NL + "\t@Override";
  protected final String TEXT_1304 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1305 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1306 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY24" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1307 = NL + "\t@Override";
  protected final String TEXT_1308 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1309 = NL + "\t\t\tcase ";
  protected final String TEXT_1310 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1311 = ";";
  protected final String TEXT_1312 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY25" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1313 = NL + "\t@Override";
  protected final String TEXT_1314 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1315 = NL + "\t\t\tcase ";
  protected final String TEXT_1316 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1317 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1318 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1319 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY26" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1320 = NL + "\t@Override";
  protected final String TEXT_1321 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1322 = "\"unchecked\"";
  protected final String TEXT_1323 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1324 = ")";
  protected final String TEXT_1325 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1326 = " arguments) throws ";
  protected final String TEXT_1327 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1328 = ")" + NL + "\t\t{";
  protected final String TEXT_1329 = NL + "\t\t\tcase ";
  protected final String TEXT_1330 = ":";
  protected final String TEXT_1331 = NL + "\t\t\t\t";
  protected final String TEXT_1332 = "(";
  protected final String TEXT_1333 = "(";
  protected final String TEXT_1334 = "(";
  protected final String TEXT_1335 = ")";
  protected final String TEXT_1336 = "arguments.get(";
  protected final String TEXT_1337 = ")";
  protected final String TEXT_1338 = ").";
  protected final String TEXT_1339 = "()";
  protected final String TEXT_1340 = ", ";
  protected final String TEXT_1341 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1342 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1343 = "new ";
  protected final String TEXT_1344 = "(";
  protected final String TEXT_1345 = "(";
  protected final String TEXT_1346 = "(";
  protected final String TEXT_1347 = "(";
  protected final String TEXT_1348 = ")";
  protected final String TEXT_1349 = "arguments.get(";
  protected final String TEXT_1350 = ")";
  protected final String TEXT_1351 = ").";
  protected final String TEXT_1352 = "()";
  protected final String TEXT_1353 = ", ";
  protected final String TEXT_1354 = ")";
  protected final String TEXT_1355 = ")";
  protected final String TEXT_1356 = ";";
  protected final String TEXT_1357 = NL + "\t\t}";
  protected final String TEXT_1358 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1359 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1360 = NL + "\t}" + NL;
  protected final String TEXT_1361 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY27" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1362 = NL + "\t@Override";
  protected final String TEXT_1363 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());" + NL + "\t\tif (data != null) result.append(data.toString());" + NL + "\t\t" + NL + "\t\treturn result.toString();" + NL + "\t\t}";
  protected final String TEXT_1364 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY28" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1365 = NL + "\t@";
  protected final String TEXT_1366 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY29" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1367 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY30" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY31" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1368 = " getKey()" + NL + "\t{";
  protected final String TEXT_1369 = NL + "\t\treturn new ";
  protected final String TEXT_1370 = "(getTypedKey());";
  protected final String TEXT_1371 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1372 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY32" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1373 = " key)" + NL + "\t{";
  protected final String TEXT_1374 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1375 = "(";
  protected final String TEXT_1376 = ")";
  protected final String TEXT_1377 = "key);";
  protected final String TEXT_1378 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1379 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1380 = ")key).";
  protected final String TEXT_1381 = "());";
  protected final String TEXT_1382 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1383 = ")key);";
  protected final String TEXT_1384 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY33" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1385 = " getValue()" + NL + "\t{";
  protected final String TEXT_1386 = NL + "\t\treturn new ";
  protected final String TEXT_1387 = "(getTypedValue());";
  protected final String TEXT_1388 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1389 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY34" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1390 = " setValue(";
  protected final String TEXT_1391 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1392 = " oldValue = getValue();";
  protected final String TEXT_1393 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1394 = "(";
  protected final String TEXT_1395 = ")";
  protected final String TEXT_1396 = "value);";
  protected final String TEXT_1397 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1398 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1399 = ")value).";
  protected final String TEXT_1400 = "());";
  protected final String TEXT_1401 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1402 = ")value);";
  protected final String TEXT_1403 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t *YY35" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1404 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1405 = NL + "\tpublic ";
  protected final String TEXT_1406 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1407 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1408 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1409 = NL + NL + NL + NL;
  protected final String TEXT_1410 = NL + "// data Class generation " + NL + "protected static  class Data";
  protected final String TEXT_1411 = NL + NL + "{" + NL;
  protected final String TEXT_1412 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_1413 = " copyright = ";
  protected final String TEXT_1414 = ";";
  protected final String TEXT_1415 = NL;
  protected final String TEXT_1416 = NL;
  protected final String TEXT_1417 = NL;
  protected final String TEXT_1418 = "// The goal of this template is to BLAH, BLAH, BLAH" + NL;
  protected final String TEXT_1419 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_1420 = "() <em>";
  protected final String TEXT_1421 = "</em>}' ";
  protected final String TEXT_1422 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1423 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1424 = NL + "\t@";
  protected final String TEXT_1425 = NL + "\tprotected ";
  protected final String TEXT_1426 = ".Internal.SettingDelegate ";
  protected final String TEXT_1427 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_1428 = ".Internal)";
  protected final String TEXT_1429 = ").getSettingDelegate();" + NL;
  protected final String TEXT_1430 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1431 = "() <em>";
  protected final String TEXT_1432 = "</em>}' ";
  protected final String TEXT_1433 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1434 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1435 = NL + "\t@";
  protected final String TEXT_1436 = NL + "\tprotected ";
  protected final String TEXT_1437 = " ";
  protected final String TEXT_1438 = ";" + NL;
  protected final String TEXT_1439 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_1440 = "() <em>";
  protected final String TEXT_1441 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1442 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1443 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1444 = NL + "\tprotected static final ";
  protected final String TEXT_1445 = "[] ";
  protected final String TEXT_1446 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_1447 = " [0]";
  protected final String TEXT_1448 = ";" + NL;
  protected final String TEXT_1449 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_1450 = "() <em>";
  protected final String TEXT_1451 = "</em>}' ";
  protected final String TEXT_1452 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1453 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1454 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1455 = NL + "\tprotected static final ";
  protected final String TEXT_1456 = " ";
  protected final String TEXT_1457 = "; // TODO The default value literal \"";
  protected final String TEXT_1458 = "\" is not valid.";
  protected final String TEXT_1459 = " = ";
  protected final String TEXT_1460 = ";";
  protected final String TEXT_1461 = NL;
  protected final String TEXT_1462 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1463 = NL + "\t@";
  protected final String TEXT_1464 = NL + "\tprotected int ";
  protected final String TEXT_1465 = " = 0;" + NL;
  protected final String TEXT_1466 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_1467 = "() <em>";
  protected final String TEXT_1468 = "</em>}' ";
  protected final String TEXT_1469 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1470 = "_EFLAG_OFFSET = ";
  protected final String TEXT_1471 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_1472 = "() <em>";
  protected final String TEXT_1473 = "</em>}' ";
  protected final String TEXT_1474 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1475 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_1476 = ".ordinal()";
  protected final String TEXT_1477 = ".VALUES.indexOf(";
  protected final String TEXT_1478 = ")";
  protected final String TEXT_1479 = " << ";
  protected final String TEXT_1480 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_1481 = " ";
  protected final String TEXT_1482 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_1483 = "[] ";
  protected final String TEXT_1484 = "_EFLAG_VALUES = ";
  protected final String TEXT_1485 = ".values()";
  protected final String TEXT_1486 = "(";
  protected final String TEXT_1487 = "[])";
  protected final String TEXT_1488 = ".VALUES.toArray(new ";
  protected final String TEXT_1489 = "[";
  protected final String TEXT_1490 = ".VALUES.size()])";
  protected final String TEXT_1491 = ";" + NL;
  protected final String TEXT_1492 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_1493 = " representing the value of the '{@link #";
  protected final String TEXT_1494 = "() <em>";
  protected final String TEXT_1495 = "</em>}' ";
  protected final String TEXT_1496 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1497 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1498 = "_EFLAG = ";
  protected final String TEXT_1499 = " << ";
  protected final String TEXT_1500 = "_EFLAG_OFFSET";
  protected final String TEXT_1501 = ";" + NL;
  protected final String TEXT_1502 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_1503 = "() <em>";
  protected final String TEXT_1504 = "</em>}' ";
  protected final String TEXT_1505 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1506 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1507 = NL + "\t@";
  protected final String TEXT_1508 = NL + "\tprotected ";
  protected final String TEXT_1509 = " ";
  protected final String TEXT_1510 = " = ";
  protected final String TEXT_1511 = ";" + NL;
  protected final String TEXT_1512 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1513 = NL + "\t@";
  protected final String TEXT_1514 = NL + "\tprotected int ";
  protected final String TEXT_1515 = " = 0;" + NL;
  protected final String TEXT_1516 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_1517 = " ";
  protected final String TEXT_1518 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_1519 = "_ESETFLAG = 1 << ";
  protected final String TEXT_1520 = ";" + NL;
  protected final String TEXT_1521 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_1522 = " ";
  protected final String TEXT_1523 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1524 = NL + "\t@";
  protected final String TEXT_1525 = NL + "\tprotected boolean ";
  protected final String TEXT_1526 = "ESet;" + NL;
  protected final String TEXT_1527 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_1528 = ".getOperationID(";
  protected final String TEXT_1529 = ") - ";
  protected final String TEXT_1530 = ";" + NL;
  protected final String TEXT_1531 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1532 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Data";
  protected final String TEXT_1533 = "()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1534 = " super(); ";
  protected final String TEXT_1535 = NL + "\t\t";
  protected final String TEXT_1536 = " |= ";
  protected final String TEXT_1537 = "_EFLAG";
  protected final String TEXT_1538 = "_DEFAULT";
  protected final String TEXT_1539 = ";";
  protected final String TEXT_1540 = NL + "\t}" + NL + "\t" + NL + "\t\t";
  protected final String TEXT_1541 = NL + "\t/**" + NL + "\t *Constructor of Data";
  protected final String TEXT_1542 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param {@link ";
  protected final String TEXT_1543 = " }" + NL + "\t * @generated" + NL + "\t */" + NL + "\t//public Data";
  protected final String TEXT_1544 = "(Data";
  protected final String TEXT_1545 = " data)" + NL + "\t//{" + NL + "\t//\tthis();\t\t" + NL + "\t//\t";
  protected final String TEXT_1546 = NL + "\t//\t";
  protected final String TEXT_1547 = " = data.";
  protected final String TEXT_1548 = ";";
  protected final String TEXT_1549 = NL + "\t//\t}" + NL + "\t";
  protected final String TEXT_1550 = NL + "\t";
  protected final String TEXT_1551 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString(){\t" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1552 = "\t\t";
  protected final String TEXT_1553 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1554 = ": \");";
  protected final String TEXT_1555 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1556 = ": \");";
  protected final String TEXT_1557 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1558 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1559 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1560 = NL + "\t\tif (";
  protected final String TEXT_1561 = "(";
  protected final String TEXT_1562 = " & ";
  protected final String TEXT_1563 = "_ESETFLAG) != 0";
  protected final String TEXT_1564 = "ESet";
  protected final String TEXT_1565 = ") result.append((";
  protected final String TEXT_1566 = " & ";
  protected final String TEXT_1567 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1568 = NL + "\t\tif (";
  protected final String TEXT_1569 = "(";
  protected final String TEXT_1570 = " & ";
  protected final String TEXT_1571 = "_ESETFLAG) != 0";
  protected final String TEXT_1572 = "ESet";
  protected final String TEXT_1573 = ") result.append(";
  protected final String TEXT_1574 = "_EFLAG_VALUES[(";
  protected final String TEXT_1575 = " & ";
  protected final String TEXT_1576 = "_EFLAG) >>> ";
  protected final String TEXT_1577 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1578 = NL + "\t\tif (";
  protected final String TEXT_1579 = "(";
  protected final String TEXT_1580 = " & ";
  protected final String TEXT_1581 = "_ESETFLAG) != 0";
  protected final String TEXT_1582 = "ESet";
  protected final String TEXT_1583 = ") result.append(";
  protected final String TEXT_1584 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1585 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1586 = ", ";
  protected final String TEXT_1587 = "));";
  protected final String TEXT_1588 = NL + "\t\tresult.append((";
  protected final String TEXT_1589 = " & ";
  protected final String TEXT_1590 = "_EFLAG) != 0);";
  protected final String TEXT_1591 = NL + "\t\tresult.append(";
  protected final String TEXT_1592 = "_EFLAG_VALUES[(";
  protected final String TEXT_1593 = " & ";
  protected final String TEXT_1594 = "_EFLAG) >>> ";
  protected final String TEXT_1595 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1596 = NL + "\t\tresult.append(";
  protected final String TEXT_1597 = ");";
  protected final String TEXT_1598 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL + "\t";
  protected final String TEXT_1599 = "\t";
  protected final String TEXT_1600 = NL + "}//end data class";
  protected final String TEXT_1601 = NL + "} //";
  protected final String TEXT_1602 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    


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
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_13);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_17);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_21);
    }
    }
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_26);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_27);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_29);
    }}
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    stringBuffer.append(TEXT_31);
    } else {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_33);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_34);
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_41);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_42);
    }
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_45);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getNonNLS());
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
    if (genModel.getRootExtendsClass().contains(genClass.getClassExtends().substring(9)) && !genModel.isReflectiveDelegation()){
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_84);
    }
    stringBuffer.append(TEXT_85);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_86);
    } else {
    stringBuffer.append(TEXT_87);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_89);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_92);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    if (!genModel.isReflectiveDelegation()){
    stringBuffer.append(TEXT_96);
    if (! genModel.getRootExtendsClass().contains(genClass.getClassExtends().substring(9)) ){
    stringBuffer.append(TEXT_97);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_103);
    }
    stringBuffer.append(TEXT_104);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_105);
    }
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_108);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    stringBuffer.append(TEXT_109);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_110);
    }
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_112);
    }
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(TEXT_114);
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_115);
    if (!isImplementation) {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_118);
    } else {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_121);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_125);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_128);
    } else {
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_131);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_133);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(TEXT_140);
    if (!isImplementation) {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_143);
    } else {
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_146);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_149);
    }
    stringBuffer.append(TEXT_150);
    if (!isImplementation) {
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_152);
    } else {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_154);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_156);
    } else {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_159);
    }
    stringBuffer.append(TEXT_160);
    }
    stringBuffer.append(TEXT_161);
    if (!isImplementation) {
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_165);
    } else {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_173);
    }
    stringBuffer.append(TEXT_174);
    if (!isImplementation) {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_177);
    } else {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_181);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_184);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_185);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_186);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_187);
    } else {
    stringBuffer.append(TEXT_188);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_189);
    }
    stringBuffer.append(TEXT_190);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_192);
    } else {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_194);
    }
    stringBuffer.append(TEXT_195);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_196);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_198);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_200);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_202);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_206);
    }
    }
    stringBuffer.append(TEXT_207);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_210);
    }
    stringBuffer.append(TEXT_211);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_213);
    }
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_216);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_219);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_221);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_224);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_227);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_229);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_230);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_232);
    }}
    stringBuffer.append(TEXT_233);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_234);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_237);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_238);
    }
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_241);
    }
    stringBuffer.append(TEXT_242);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_243);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_244);
    }
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_249);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_251);
    }
    stringBuffer.append(TEXT_252);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_253);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_254);
    }
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_257);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_261);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_262);
    }
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_265);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_267);
    }
    stringBuffer.append(TEXT_268);
    } 
	else if (!genFeature.isVolatile()) {
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_273);
    }
    stringBuffer.append(TEXT_274);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_275);
    if (!genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_277);
    }else{
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_279);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_283);
    } else {
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_287);
    }
    stringBuffer.append(TEXT_288);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_289);
    } 	  	  
	  else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_298);
    } 	  
	  else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_305);
    }
    stringBuffer.append(TEXT_306);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfResource"));
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_309);
    }
    stringBuffer.append(TEXT_310);
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfNotification"));
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_317);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfNotification"));
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_323);
    } else {
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfNotification"));
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_333);
    }
    } else {
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genModel.getImportedName("fr.inria.atlanmod.neo4emf.INeo4emfNotification"));
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_336);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_337);
    }
    }
    } 	
	else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_347);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_351);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_354);
    } else {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_356);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_357);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_359);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_361);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_363);
    } else {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_366);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
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
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_372);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_374);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_376);
    }
    stringBuffer.append(TEXT_377);
    } else {
    stringBuffer.append(TEXT_378);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_379);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_381);
    }
    stringBuffer.append(TEXT_382);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_384);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_386);
    }
    stringBuffer.append(TEXT_387);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_392);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_393);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_394);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_395);
    } else {
    stringBuffer.append(TEXT_396);
    }
    stringBuffer.append(TEXT_397);
    }
    stringBuffer.append(TEXT_398);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_399);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_400);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_403);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_408);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_409);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_410);
    }
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_413);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_415);
    }
    stringBuffer.append(TEXT_416);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_419);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_422);
    } else {
    stringBuffer.append(TEXT_423);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_424);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_429);
    } else {
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_431);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_433);
    }
    } else {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_436);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_437);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_438);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_444);
     if (! genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && ! genFeature.hasSettingDelegate() && ! genFeature.isContainer() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_445);
    }
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_449);
    stringBuffer.append(TEXT_450);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_454);
    stringBuffer.append(TEXT_455);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_459);
    } else {
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_463);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_465);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_467);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_471);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_474);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_477);
    }
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_479);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_480);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_485);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_489);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_490);
    } else {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_492);
    }
    stringBuffer.append(TEXT_493);
    } else {
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_498);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_503);
    }
    stringBuffer.append(TEXT_504);
    }
    stringBuffer.append(TEXT_505);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_508);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_511);
    } else {
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_516);
    }
    } else {
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_519);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_520);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_528);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_531);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_533);
    }
    }
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_535);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_536);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_539);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_541);
    }
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_543);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_544);
    stringBuffer.append(TEXT_545);
     if (!genModel.isDynamicDelegation() && !genModel.isReflectiveDelegation() && !genFeature.hasSettingDelegate() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_546);
    if (CodegenUtil.getDataClassExtends(genClass) != ""){
    stringBuffer.append(TEXT_547);
    }}
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
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
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
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
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_564);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_566);
    }
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_568);
    }
    stringBuffer.append(TEXT_569);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_582);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_583);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_587);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_593);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_598);
    }
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_600);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_602);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_603);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_605);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
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
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_615);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_619);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_620);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_621);
    }
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_625);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_626);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_629);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_633);
    }
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_636);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_639);
    }
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_641);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_648);
    }
    stringBuffer.append(TEXT_649);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_655);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_661);
    } else {
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_669);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_677);
    } else {
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_685);
    if (isJDK50) {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_687);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_689);
    }
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_691);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_694);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_695);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_702);
    } else {
    stringBuffer.append(TEXT_703);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_705);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_707);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_712);
    } else {
    stringBuffer.append(TEXT_713);
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_716);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_720);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_722);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_726);
    }
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_729);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_732);
    }
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_734);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_738);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_743);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_745);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_746);
    } else {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_748);
    }
    stringBuffer.append(TEXT_749);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_753);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_755);
    stringBuffer.append( genFeature.getEDefault().equals("null") ? "" : "Data"+genClass.getInterfaceName()+".");
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getCapName());
    }else {
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_758);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append((genModel.isVirtualDelegation() && !genFeature.isPrimitiveType() )== true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_760);
    }
    }
    }
    } 
	else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
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
    } else {
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_772);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_774);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_776);
    }
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_778);
    }
    stringBuffer.append(TEXT_779);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_780);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_783);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_784);
    }
    //Class/setGenFeature.override.javajetinc
    }
    stringBuffer.append(TEXT_785);
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_786);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_790);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_792);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_795);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_797);
    }
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_799);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_803);
    }
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_805);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_807);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_811);
    }
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_814);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_817);
    }
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_819);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_824);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_826);
    } else {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_828);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_829);
    } else {
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_831);
    }
    stringBuffer.append(TEXT_832);
    }
    } else {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_835);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_836);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    }
    stringBuffer.append(TEXT_837);
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    stringBuffer.append(TEXT_838);
    stringBuffer.append(TEXT_839);
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_846);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_849);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_856);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_859);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_860);
    stringBuffer.append(TEXT_861);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_862);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_868);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_869);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_870);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_871);
    }}
    stringBuffer.append(TEXT_872);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_873);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_878);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_879);
    }
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_884);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_888);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_889);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_893);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_895);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_896);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_897);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_898);
    } else {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_900);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_903);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_904);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_905);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_909);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_911);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_912);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_913);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_914);
    if (size > 0) {
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_916);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_918);
    } else {
    stringBuffer.append(TEXT_919);
    }
    stringBuffer.append(TEXT_920);
    } else {
    stringBuffer.append(TEXT_921);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_922);
    }
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_924);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_925);
    if (size > 0) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_927);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_929);
    } else {
    stringBuffer.append(TEXT_930);
    }
    stringBuffer.append(TEXT_931);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_933);
    }
    stringBuffer.append(TEXT_934);
    }
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_937);
    } else {
    stringBuffer.append(TEXT_938);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_939);
    }
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_940);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_941);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_942);
    }
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_946);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_947);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_949);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_950);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_954);
    } else {
    stringBuffer.append(TEXT_955);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_956);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_957);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_960);
    } else {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_962);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_967);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_970);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_972);
    }
    stringBuffer.append(TEXT_973);
    stringBuffer.append( (genModel.isVirtualDelegation()|| genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_974);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_976);
    stringBuffer.append( (genModel.isVirtualDelegation() || genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_978);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_980);
    stringBuffer.append( (genModel.isVirtualDelegation()|| genModel.isReflectiveDelegation())==true ? "":"getData().");
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_982);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_983);
    }
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_986);
    }
    }
    stringBuffer.append(TEXT_987);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_988);
    } else {
    stringBuffer.append(TEXT_989);
    }
    stringBuffer.append(TEXT_990);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_991);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_992);
    }
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_996);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_997);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_999);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1004);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1008);
    } else {
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1011);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1013);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1015);
    } else {
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1017);
    }
    }
    stringBuffer.append(TEXT_1018);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1019);
    } else {
    stringBuffer.append(TEXT_1020);
    }
    stringBuffer.append(TEXT_1021);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1022);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1023);
    }
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1027);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1031);
    }
    stringBuffer.append(TEXT_1032);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1033);
    } else {
    stringBuffer.append(TEXT_1034);
    }
    stringBuffer.append(TEXT_1035);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1036);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1037);
    }
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1039);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1041);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1043);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1045);
    } else {
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1048);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1051);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1056);
    } else {
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1059);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1063);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1067);
    } else {
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1069);
    }
    }
    stringBuffer.append(TEXT_1070);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1071);
    } else {
    stringBuffer.append(TEXT_1072);
    }
    stringBuffer.append(TEXT_1073);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1074);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1075);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1076);
    }
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1078);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1080);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1084);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1087);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1092);
    } else {
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1095);
    }
    } else {
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1100);
    }
    stringBuffer.append(TEXT_1101);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1105);
    } else {
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1107);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1109);
    }
    stringBuffer.append(TEXT_1110);
    }
    stringBuffer.append(TEXT_1111);
    }
    stringBuffer.append(TEXT_1112);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1113);
    } else {
    stringBuffer.append(TEXT_1114);
    }
    stringBuffer.append(TEXT_1115);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(TEXT_1117);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1118);
    }
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1120);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1122);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1125);
    } else {
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1127);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1129);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1132);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1134);
    } else {
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1138);
    }
    stringBuffer.append(TEXT_1139);
    }
    stringBuffer.append(TEXT_1140);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1141);
    } else {
    stringBuffer.append(TEXT_1142);
    }
    stringBuffer.append(TEXT_1143);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(TEXT_1145);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1146);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1147);
    }
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1149);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1151);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1153);
    } else {
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1155);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1158);
    } else {
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1161);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1164);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1171);
    } else {
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1173);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1175);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1177);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1179);
    } else {
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1181);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1183);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1185);
    } else {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1187);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1193);
    } else {
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1199);
    }
    } else {
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1203);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1209);
    } else {
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1213);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1220);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1233);
    } else {
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1240);
    }
    }
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
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1245);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1246);
    }
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1248);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1251);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1254);
    }
    stringBuffer.append(TEXT_1255);
    }
    stringBuffer.append(TEXT_1256);
    }
    stringBuffer.append(TEXT_1257);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1258);
    }
    stringBuffer.append(TEXT_1259);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1260);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1262);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1265);
    }
    stringBuffer.append(TEXT_1266);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1269);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1272);
    }
    stringBuffer.append(TEXT_1273);
    }
    stringBuffer.append(TEXT_1274);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1275);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1276);
    }
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1278);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1280);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1283);
    }
    }
    stringBuffer.append(TEXT_1284);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1286);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1289);
    }
    stringBuffer.append(TEXT_1290);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1293);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1296);
    }
    stringBuffer.append(TEXT_1297);
    }
    stringBuffer.append(TEXT_1298);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1299);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1300);
    }
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1302);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1303);
    }
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1305);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1306);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1307);
    }
    stringBuffer.append(TEXT_1308);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1311);
    }
    stringBuffer.append(TEXT_1312);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1313);
    }
    stringBuffer.append(TEXT_1314);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1316);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1317);
    }
    stringBuffer.append(TEXT_1318);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1319);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1320);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1321);
    if (!isRaw) {
    stringBuffer.append(TEXT_1322);
    } else {
    stringBuffer.append(TEXT_1323);
    }
    stringBuffer.append(TEXT_1324);
    }
    }
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1328);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1330);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1332);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1333);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1334);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1335);
    }
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1337);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1339);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1340);
    }
    }
    stringBuffer.append(TEXT_1341);
    } else {
    stringBuffer.append(TEXT_1342);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1344);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1345);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1346);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1347);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1348);
    }
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1350);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1352);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1353);
    }
    }
    stringBuffer.append(TEXT_1354);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1355);
    }
    stringBuffer.append(TEXT_1356);
    }
    }
    stringBuffer.append(TEXT_1357);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1358);
    } else {
    stringBuffer.append(TEXT_1359);
    }
    stringBuffer.append(TEXT_1360);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1361);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1362);
    }
    stringBuffer.append(TEXT_1363);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1364);
    if (isGWT) {
    stringBuffer.append(TEXT_1365);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1368);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1370);
    } else {
    stringBuffer.append(TEXT_1371);
    }
    stringBuffer.append(TEXT_1372);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1373);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1374);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1376);
    }
    stringBuffer.append(TEXT_1377);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1378);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1381);
    } else {
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1383);
    }
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1385);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1387);
    } else {
    stringBuffer.append(TEXT_1388);
    }
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1392);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1393);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1395);
    }
    stringBuffer.append(TEXT_1396);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1397);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1398);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1400);
    } else {
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1402);
    }
    stringBuffer.append(TEXT_1403);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1404);
    }
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1408);
    }
    stringBuffer.append(TEXT_1409);
    if (isImplementation && (!(genModel.isReflectiveDelegation()))) {
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(CodegenUtil.getDataClassExtends(genClass));
    stringBuffer.append(TEXT_1411);
     if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1415);
    }
    stringBuffer.append(TEXT_1416);
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(TEXT_1418);
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1423);
    if (isGWT) {
    stringBuffer.append(TEXT_1424);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1425);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1429);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1430);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1431);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1432);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1433);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1434);
    if (isGWT) {
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1436);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_1437);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1438);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_1439);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1440);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1441);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_1442);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_1443);
    }
    stringBuffer.append(TEXT_1444);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_1448);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1453);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_1454);
    }
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_1458);
    } else {
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_1460);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_1461);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1462);
    if (isGWT) {
    stringBuffer.append(TEXT_1463);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1464);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1465);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_1466);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1469);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1472);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1474);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1475);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1476);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1477);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1478);
    }
    stringBuffer.append(TEXT_1479);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1483);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1484);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1485);
    } else {
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1490);
    }
    stringBuffer.append(TEXT_1491);
    }
    stringBuffer.append(TEXT_1492);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1494);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1495);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1497);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1498);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_1499);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1500);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_1501);
    } else {
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1505);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1506);
    if (isGWT) {
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1508);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1510);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1511);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_1512);
    if (isGWT) {
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1514);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1515);
    }
    stringBuffer.append(TEXT_1516);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1517);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1518);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_1520);
    } else {
    stringBuffer.append(TEXT_1521);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1523);
    if (isGWT) {
    stringBuffer.append(TEXT_1524);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1525);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1526);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1528);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1529);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_1530);
    }
    if (isImplementation ) {
    stringBuffer.append(TEXT_1531);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1533);
    if ( CodegenUtil.getDataClassExtends(genClass) !=""){
    stringBuffer.append(TEXT_1534);
    }
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_1535);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1536);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1537);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1538);
    }
    stringBuffer.append(TEXT_1539);
    }
    stringBuffer.append(TEXT_1540);
    if ( CodegenUtil.getDataClassExtends(genClass) !=""){
    stringBuffer.append(TEXT_1541);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1542);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
    stringBuffer.append(TEXT_1543);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_1544);
    stringBuffer.append(genClass.getClassExtendsGenClass().getInterfaceName());
    stringBuffer.append(TEXT_1545);
     for (GenFeature genFeat : genClass.getClassExtendsGenClass().getDeclaredFieldGenFeatures()){
    stringBuffer.append(TEXT_1546);
    stringBuffer.append(genFeat.getSafeName());
    stringBuffer.append(TEXT_1547);
    stringBuffer.append(genFeat.getSafeName());
    stringBuffer.append(TEXT_1548);
    }
    stringBuffer.append(TEXT_1549);
    }
    stringBuffer.append(TEXT_1550);
    if (!genModel.isDynamicDelegation() && !genModel.isVirtualDelegation()){
    stringBuffer.append(TEXT_1551);
    { boolean first = true;
    stringBuffer.append(TEXT_1552);
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1553);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1554);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1555);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1556);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1557);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1558);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1559);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1560);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1562);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1563);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1564);
    }
    stringBuffer.append(TEXT_1565);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1566);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1568);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1569);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1570);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1571);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1572);
    }
    stringBuffer.append(TEXT_1573);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1574);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1575);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1576);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1577);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1578);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1579);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1580);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1581);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1582);
    }
    stringBuffer.append(TEXT_1583);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1584);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1585);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1586);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1587);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1588);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1589);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1590);
    } else {
    stringBuffer.append(TEXT_1591);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1592);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1593);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1594);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1595);
    }
    } else {
    stringBuffer.append(TEXT_1596);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1597);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1598);
    }
    stringBuffer.append(TEXT_1599);
    }
    stringBuffer.append(TEXT_1600);
    } 
    stringBuffer.append(TEXT_1601);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1602);
    return stringBuffer.toString();
  }
}
