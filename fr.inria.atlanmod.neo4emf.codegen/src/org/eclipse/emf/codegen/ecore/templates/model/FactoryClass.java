package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class FactoryClass
{
  protected static String nl;
  public static synchronized FactoryClass create(String lineSeparator)
  {
    nl = lineSeparator;
    FactoryClass result = new FactoryClass();
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
  protected final String TEXT_10 = NL + NL;
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Factory</b> for the model." + NL + " * It provides a create method for each non-abstract class of the model." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_13 = NL + " * @see ";
  protected final String TEXT_14 = NL + " * @generated" + NL + " */";
  protected final String TEXT_15 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Factory</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */";
  protected final String TEXT_16 = NL + "public class ";
  protected final String TEXT_17 = " extends ";
  protected final String TEXT_18 = " implements ";
  protected final String TEXT_19 = NL + "public interface ";
  protected final String TEXT_20 = " extends ";
  protected final String TEXT_21 = NL + "{";
  protected final String TEXT_22 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_23 = " copyright = ";
  protected final String TEXT_24 = ";";
  protected final String TEXT_25 = NL;
  protected final String TEXT_26 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_27 = " eINSTANCE = init();" + NL;
  protected final String TEXT_28 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_29 = " INSTANCE = ";
  protected final String TEXT_30 = ".eINSTANCE;" + NL;
  protected final String TEXT_31 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_32 = " eINSTANCE = ";
  protected final String TEXT_33 = ".init();" + NL;
  protected final String TEXT_34 = NL + NL + "\t";
  protected final String TEXT_35 = NL + "\t/**" + NL + "\t * AdapterFactory instance" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "" + NL + "\tprotected ";
  protected final String TEXT_36 = " adapterFactory = new ";
  protected final String TEXT_37 = "();" + NL + "\t";
  protected final String TEXT_38 = NL + "\t" + NL + "\t/**" + NL + "\t * Creates the default factory implementation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_39 = NL + "\tpublic static ";
  protected final String TEXT_40 = " init()" + NL + "\t{" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_41 = " the";
  protected final String TEXT_42 = " = (";
  protected final String TEXT_43 = ")";
  protected final String TEXT_44 = ".Registry.INSTANCE.getEFactory(\"";
  protected final String TEXT_45 = "\");";
  protected final String TEXT_46 = " " + NL + "\t\t\tif (the";
  protected final String TEXT_47 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn the";
  protected final String TEXT_48 = ";" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (Exception exception)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_49 = ".INSTANCE.log(exception);" + NL + "\t\t}" + NL + "\t\treturn new ";
  protected final String TEXT_50 = "();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_51 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_52 = NL + "\t@Override";
  protected final String TEXT_53 = NL + "\tpublic EObject create(EClass eClass)" + NL + "\t{" + NL + "\t\tswitch (eClass.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_54 = NL + "\t\t\tcase ";
  protected final String TEXT_55 = ".";
  protected final String TEXT_56 = ": return ";
  protected final String TEXT_57 = "create";
  protected final String TEXT_58 = "();";
  protected final String TEXT_59 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The class '\" + eClass.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_60 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_61 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_62 = NL + "\t@Override";
  protected final String TEXT_63 = NL + "\tpublic Object createFromString(";
  protected final String TEXT_64 = " eDataType, String initialValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_65 = NL + "\t\t\tcase ";
  protected final String TEXT_66 = ".";
  protected final String TEXT_67 = ":" + NL + "\t\t\t\treturn create";
  protected final String TEXT_68 = "FromString(eDataType, initialValue);";
  protected final String TEXT_69 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_70 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_71 = NL + "\t@Override";
  protected final String TEXT_72 = NL + "\tpublic String convertToString(";
  protected final String TEXT_73 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_74 = NL + "\t\t\tcase ";
  protected final String TEXT_75 = ".";
  protected final String TEXT_76 = ":" + NL + "\t\t\t\treturn convert";
  protected final String TEXT_77 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_78 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_79 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_80 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_81 = " create";
  protected final String TEXT_82 = "()" + NL + "\t{";
  protected final String TEXT_83 = NL + "\t\t";
  protected final String TEXT_84 = " ";
  protected final String TEXT_85 = " = ";
  protected final String TEXT_86 = "super.create(";
  protected final String TEXT_87 = ");";
  protected final String TEXT_88 = NL + "\t\t";
  protected final String TEXT_89 = " ";
  protected final String TEXT_90 = " = new ";
  protected final String TEXT_91 = "()";
  protected final String TEXT_92 = "{}";
  protected final String TEXT_93 = ";" + NL + "\t\t";
  protected final String TEXT_94 = ".eAdapters().add(adapterFactory.create";
  protected final String TEXT_95 = "Adapter());" + NL + "\t\tChangeLog.getInstance().add(new NewObject(";
  protected final String TEXT_96 = "));";
  protected final String TEXT_97 = NL + "\t\treturn ";
  protected final String TEXT_98 = ";" + NL + "\t}" + NL;
  protected final String TEXT_99 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_100 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_101 = NL + "\tpublic ";
  protected final String TEXT_102 = " create";
  protected final String TEXT_103 = "(String ";
  protected final String TEXT_104 = "it";
  protected final String TEXT_105 = "literal";
  protected final String TEXT_106 = ")" + NL + "\t{";
  protected final String TEXT_107 = NL + "\t\t";
  protected final String TEXT_108 = NL + "\t\t";
  protected final String TEXT_109 = " result = ";
  protected final String TEXT_110 = ".get(literal);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + literal + \"' is not a valid enumerator of '\" + ";
  protected final String TEXT_111 = ".getName() + \"'\");";
  protected final String TEXT_112 = NL + "\t\treturn result;";
  protected final String TEXT_113 = NL + "\t\treturn new ";
  protected final String TEXT_114 = "(create";
  protected final String TEXT_115 = "(literal));";
  protected final String TEXT_116 = NL + "\t\treturn create";
  protected final String TEXT_117 = "(literal);";
  protected final String TEXT_118 = NL + "\t\treturn new ";
  protected final String TEXT_119 = "(";
  protected final String TEXT_120 = ".create";
  protected final String TEXT_121 = "(literal));";
  protected final String TEXT_122 = NL + "\t\treturn ";
  protected final String TEXT_123 = ".create";
  protected final String TEXT_124 = "(literal);";
  protected final String TEXT_125 = NL + "\t\treturn ";
  protected final String TEXT_126 = "(";
  protected final String TEXT_127 = ")";
  protected final String TEXT_128 = ".createFromString(";
  protected final String TEXT_129 = ", literal);";
  protected final String TEXT_130 = NL + "\t\tif (literal == null) return null;" + NL + "\t\t";
  protected final String TEXT_131 = " result = new ";
  protected final String TEXT_132 = "<";
  protected final String TEXT_133 = ">";
  protected final String TEXT_134 = "();";
  protected final String TEXT_135 = NL + "\t\tfor (";
  protected final String TEXT_136 = " stringTokenizer = new ";
  protected final String TEXT_137 = "(literal); stringTokenizer.hasMoreTokens(); )";
  protected final String TEXT_138 = NL + "\t\tfor (String item : split(literal))";
  protected final String TEXT_139 = NL + "\t\t{";
  protected final String TEXT_140 = NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_141 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_142 = "(item));";
  protected final String TEXT_143 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_144 = "FromString(";
  protected final String TEXT_145 = ", item));";
  protected final String TEXT_146 = NL + "\t\t\tresult.add(";
  protected final String TEXT_147 = ".create";
  protected final String TEXT_148 = "(item));";
  protected final String TEXT_149 = NL + "\t\t\tresult.add(";
  protected final String TEXT_150 = ".createFromString(";
  protected final String TEXT_151 = ", item));";
  protected final String TEXT_152 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_153 = NL + "\t\tif (literal == null) return ";
  protected final String TEXT_154 = ";" + NL + "\t\t";
  protected final String TEXT_155 = " result = ";
  protected final String TEXT_156 = ";" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_157 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_158 = NL + "\t\t\tresult = create";
  protected final String TEXT_159 = "(literal);";
  protected final String TEXT_160 = NL + "\t\t\tresult = (";
  protected final String TEXT_161 = ")create";
  protected final String TEXT_162 = "FromString(";
  protected final String TEXT_163 = ", literal);";
  protected final String TEXT_164 = NL + "\t\t\tresult = ";
  protected final String TEXT_165 = ".create";
  protected final String TEXT_166 = "(literal);";
  protected final String TEXT_167 = NL + "\t\t\tresult = (";
  protected final String TEXT_168 = ")";
  protected final String TEXT_169 = ".createFromString(";
  protected final String TEXT_170 = ", literal);";
  protected final String TEXT_171 = NL + "\t\t\tif (";
  protected final String TEXT_172 = "result != null && ";
  protected final String TEXT_173 = ".INSTANCE.validate(";
  protected final String TEXT_174 = ", ";
  protected final String TEXT_175 = "new ";
  protected final String TEXT_176 = "(result)";
  protected final String TEXT_177 = "result";
  protected final String TEXT_178 = ", null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_179 = NL + "\t\tif (";
  protected final String TEXT_180 = "result != null || ";
  protected final String TEXT_181 = "exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_182 = NL + "\t\treturn (";
  protected final String TEXT_183 = ")super.createFromString(literal);";
  protected final String TEXT_184 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_185 = "();";
  protected final String TEXT_186 = NL + "\t\treturn ((";
  protected final String TEXT_187 = ")super.createFromString(";
  protected final String TEXT_188 = ", literal)).";
  protected final String TEXT_189 = "();";
  protected final String TEXT_190 = NL + "\t\treturn ";
  protected final String TEXT_191 = "(";
  protected final String TEXT_192 = ")";
  protected final String TEXT_193 = "super.createFromString(";
  protected final String TEXT_194 = ", literal);";
  protected final String TEXT_195 = NL + "\t}" + NL;
  protected final String TEXT_196 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_197 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_198 = NL + "\tpublic ";
  protected final String TEXT_199 = " create";
  protected final String TEXT_200 = "FromString(";
  protected final String TEXT_201 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_202 = NL + "\t\treturn create";
  protected final String TEXT_203 = "(initialValue);";
  protected final String TEXT_204 = NL + "\t\t";
  protected final String TEXT_205 = " result = ";
  protected final String TEXT_206 = ".get(initialValue);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + initialValue + \"' is not a valid enumerator of '\" + eDataType.getName() + \"'\");";
  protected final String TEXT_207 = NL + "\t\treturn result;";
  protected final String TEXT_208 = NL + "\t\treturn ";
  protected final String TEXT_209 = "(";
  protected final String TEXT_210 = ")";
  protected final String TEXT_211 = "create";
  protected final String TEXT_212 = "FromString(";
  protected final String TEXT_213 = ", initialValue);";
  protected final String TEXT_214 = NL + "\t\treturn ";
  protected final String TEXT_215 = "(";
  protected final String TEXT_216 = ")";
  protected final String TEXT_217 = ".createFromString(";
  protected final String TEXT_218 = ", initialValue);";
  protected final String TEXT_219 = NL + "\t\treturn create";
  protected final String TEXT_220 = "(initialValue);";
  protected final String TEXT_221 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_222 = " result = new ";
  protected final String TEXT_223 = "<";
  protected final String TEXT_224 = ">";
  protected final String TEXT_225 = "();";
  protected final String TEXT_226 = NL + "\t\tfor (";
  protected final String TEXT_227 = " stringTokenizer = new ";
  protected final String TEXT_228 = "(initialValue); stringTokenizer.hasMoreTokens(); )";
  protected final String TEXT_229 = NL + "\t\tfor (String item : split(initialValue))";
  protected final String TEXT_230 = NL + "\t\t{";
  protected final String TEXT_231 = NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_232 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_233 = "FromString(";
  protected final String TEXT_234 = ", item));";
  protected final String TEXT_235 = NL + "\t\t\tresult.add(";
  protected final String TEXT_236 = "(";
  protected final String TEXT_237 = ")";
  protected final String TEXT_238 = ".createFromString(";
  protected final String TEXT_239 = ", item));";
  protected final String TEXT_240 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_241 = NL + "\t\treturn new ";
  protected final String TEXT_242 = "(create";
  protected final String TEXT_243 = "(initialValue));";
  protected final String TEXT_244 = NL + "\t\treturn create";
  protected final String TEXT_245 = "(initialValue);";
  protected final String TEXT_246 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_247 = " result = null;" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_248 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_249 = NL + "\t\t\tresult = ";
  protected final String TEXT_250 = "(";
  protected final String TEXT_251 = ")";
  protected final String TEXT_252 = "create";
  protected final String TEXT_253 = "FromString(";
  protected final String TEXT_254 = ", initialValue);";
  protected final String TEXT_255 = NL + "\t\t\tresult = ";
  protected final String TEXT_256 = "(";
  protected final String TEXT_257 = ")";
  protected final String TEXT_258 = ".createFromString(";
  protected final String TEXT_259 = ", initialValue);";
  protected final String TEXT_260 = NL + "\t\t\tif (result != null && ";
  protected final String TEXT_261 = ".INSTANCE.validate(eDataType, result, null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_262 = NL + "\t\tif (result != null || exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_263 = NL + "\t\treturn create";
  protected final String TEXT_264 = "(initialValue);";
  protected final String TEXT_265 = NL + "\t\treturn ";
  protected final String TEXT_266 = "(";
  protected final String TEXT_267 = ")";
  protected final String TEXT_268 = "super.createFromString(initialValue);";
  protected final String TEXT_269 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_270 = "();";
  protected final String TEXT_271 = NL + "\t\treturn ";
  protected final String TEXT_272 = "(";
  protected final String TEXT_273 = ")";
  protected final String TEXT_274 = "super.createFromString(eDataType, initialValue);";
  protected final String TEXT_275 = NL + "\t}" + NL;
  protected final String TEXT_276 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convert";
  protected final String TEXT_277 = "(";
  protected final String TEXT_278 = " ";
  protected final String TEXT_279 = "it";
  protected final String TEXT_280 = "instanceValue";
  protected final String TEXT_281 = ")" + NL + "\t{";
  protected final String TEXT_282 = NL + "\t\t";
  protected final String TEXT_283 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_284 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_285 = "(instanceValue";
  protected final String TEXT_286 = ".";
  protected final String TEXT_287 = "()";
  protected final String TEXT_288 = ");";
  protected final String TEXT_289 = NL + "\t\treturn convert";
  protected final String TEXT_290 = "(instanceValue);";
  protected final String TEXT_291 = NL + "\t\treturn ";
  protected final String TEXT_292 = ".convert";
  protected final String TEXT_293 = "(instanceValue);";
  protected final String TEXT_294 = NL + "\t\treturn ";
  protected final String TEXT_295 = ".convertToString(";
  protected final String TEXT_296 = ", instanceValue);";
  protected final String TEXT_297 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\tif (instanceValue.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_298 = " result = new ";
  protected final String TEXT_299 = "();";
  protected final String TEXT_300 = NL + "\t\tfor (";
  protected final String TEXT_301 = " i = instanceValue.iterator(); i.hasNext(); )";
  protected final String TEXT_302 = NL + "\t\tfor (";
  protected final String TEXT_303 = " item : instanceValue)";
  protected final String TEXT_304 = NL + "\t\t{";
  protected final String TEXT_305 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_306 = "((";
  protected final String TEXT_307 = ")";
  protected final String TEXT_308 = "));";
  protected final String TEXT_309 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_310 = "ToString(";
  protected final String TEXT_311 = ", ";
  protected final String TEXT_312 = "));";
  protected final String TEXT_313 = NL + "\t\t\tresult.append(";
  protected final String TEXT_314 = ".convert";
  protected final String TEXT_315 = "((";
  protected final String TEXT_316 = ")";
  protected final String TEXT_317 = "));";
  protected final String TEXT_318 = NL + "\t\t\tresult.append(";
  protected final String TEXT_319 = ".convertToString(";
  protected final String TEXT_320 = ", ";
  protected final String TEXT_321 = "));";
  protected final String TEXT_322 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_323 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_324 = NL + "\t\tif (";
  protected final String TEXT_325 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_326 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_327 = "(instanceValue);";
  protected final String TEXT_328 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_329 = "(((";
  protected final String TEXT_330 = ")instanceValue).";
  protected final String TEXT_331 = "());";
  protected final String TEXT_332 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_333 = "((";
  protected final String TEXT_334 = ")instanceValue);";
  protected final String TEXT_335 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_336 = "ToString(";
  protected final String TEXT_337 = ", instanceValue);";
  protected final String TEXT_338 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_339 = ".convert";
  protected final String TEXT_340 = "((";
  protected final String TEXT_341 = ")instanceValue);";
  protected final String TEXT_342 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_343 = ".convertToString(";
  protected final String TEXT_344 = ", instanceValue);";
  protected final String TEXT_345 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_346 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_347 = NL + "\t\t\tString value = convert";
  protected final String TEXT_348 = "(instanceValue);";
  protected final String TEXT_349 = NL + "\t\t\tString value = convert";
  protected final String TEXT_350 = "ToString(";
  protected final String TEXT_351 = ", ";
  protected final String TEXT_352 = "new ";
  protected final String TEXT_353 = "(instanceValue)";
  protected final String TEXT_354 = "instanceValue";
  protected final String TEXT_355 = ");";
  protected final String TEXT_356 = NL + "\t\t\tString value = ";
  protected final String TEXT_357 = ".convert";
  protected final String TEXT_358 = "(instanceValue);";
  protected final String TEXT_359 = NL + "\t\t\tString value = ";
  protected final String TEXT_360 = ".convertToString(";
  protected final String TEXT_361 = ", ";
  protected final String TEXT_362 = "new ";
  protected final String TEXT_363 = "(instanceValue)";
  protected final String TEXT_364 = "instanceValue";
  protected final String TEXT_365 = ");";
  protected final String TEXT_366 = NL + "\t\t\tif (value != null) return value;" + NL + "\t\t}" + NL + "\t\tcatch (Exception e)" + NL + "\t\t{" + NL + "\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t}";
  protected final String TEXT_367 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+";
  protected final String TEXT_368 = ".getName());";
  protected final String TEXT_369 = NL + "\t\treturn super.convertToString(instanceValue);";
  protected final String TEXT_370 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_371 = "();";
  protected final String TEXT_372 = NL + "\t\treturn super.convertToString(";
  protected final String TEXT_373 = ", new ";
  protected final String TEXT_374 = "(instanceValue));";
  protected final String TEXT_375 = NL + "\t\treturn super.convertToString(";
  protected final String TEXT_376 = ", instanceValue);";
  protected final String TEXT_377 = NL + "\t}" + NL;
  protected final String TEXT_378 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_379 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_380 = NL + "\tpublic String convert";
  protected final String TEXT_381 = "ToString(";
  protected final String TEXT_382 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_383 = NL + "\t\treturn convert";
  protected final String TEXT_384 = "((";
  protected final String TEXT_385 = ")instanceValue);";
  protected final String TEXT_386 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_387 = NL + "\t\treturn convert";
  protected final String TEXT_388 = "ToString(";
  protected final String TEXT_389 = ", instanceValue);";
  protected final String TEXT_390 = NL + "\t\treturn ";
  protected final String TEXT_391 = ".convertToString(";
  protected final String TEXT_392 = ", instanceValue);";
  protected final String TEXT_393 = NL + "\t\treturn convert";
  protected final String TEXT_394 = "((";
  protected final String TEXT_395 = ")instanceValue);";
  protected final String TEXT_396 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_397 = " list = (";
  protected final String TEXT_398 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_399 = " result = new ";
  protected final String TEXT_400 = "();";
  protected final String TEXT_401 = NL + "\t\tfor (";
  protected final String TEXT_402 = " i = list.iterator(); i.hasNext(); )";
  protected final String TEXT_403 = NL + "\t\tfor (";
  protected final String TEXT_404 = " item : list)";
  protected final String TEXT_405 = NL + "\t\t{";
  protected final String TEXT_406 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_407 = "ToString(";
  protected final String TEXT_408 = ", ";
  protected final String TEXT_409 = "));";
  protected final String TEXT_410 = NL + "\t\t\tresult.append(";
  protected final String TEXT_411 = ".convertToString(";
  protected final String TEXT_412 = ", ";
  protected final String TEXT_413 = "));";
  protected final String TEXT_414 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_415 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_416 = "(((";
  protected final String TEXT_417 = ")instanceValue)";
  protected final String TEXT_418 = ".";
  protected final String TEXT_419 = "()";
  protected final String TEXT_420 = ");";
  protected final String TEXT_421 = NL + "\t\treturn convert";
  protected final String TEXT_422 = "(instanceValue);";
  protected final String TEXT_423 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_424 = NL + "\t\tif (";
  protected final String TEXT_425 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_426 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_427 = "ToString(";
  protected final String TEXT_428 = ", instanceValue);";
  protected final String TEXT_429 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_430 = ".convertToString(";
  protected final String TEXT_431 = ", instanceValue);";
  protected final String TEXT_432 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_433 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+eDataType.getName());";
  protected final String TEXT_434 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_435 = "(";
  protected final String TEXT_436 = "(";
  protected final String TEXT_437 = "(";
  protected final String TEXT_438 = ")instanceValue";
  protected final String TEXT_439 = ").";
  protected final String TEXT_440 = "()";
  protected final String TEXT_441 = ");";
  protected final String TEXT_442 = NL + "\t\treturn convert";
  protected final String TEXT_443 = "((";
  protected final String TEXT_444 = ")instanceValue);";
  protected final String TEXT_445 = NL + "\t\treturn super.convertToString(instanceValue);";
  protected final String TEXT_446 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_447 = "();";
  protected final String TEXT_448 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_449 = NL + "\t}" + NL;
  protected final String TEXT_450 = NL + "\t/**" + NL + "\t * Returns a new object of class '<em>";
  protected final String TEXT_451 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return a new object of class '<em>";
  protected final String TEXT_452 = "</em>'." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_453 = " create";
  protected final String TEXT_454 = "();" + NL;
  protected final String TEXT_455 = NL + "\t/**" + NL + "\t * Returns an instance of data type '<em>";
  protected final String TEXT_456 = "</em>' corresponding the given literal." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param literal a literal of the data type." + NL + "\t * @return a new instance value of the data type." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_457 = " create";
  protected final String TEXT_458 = "(String literal);" + NL + "" + NL + "\t/**" + NL + "\t * Returns a literal representation of an instance of data type '<em>";
  protected final String TEXT_459 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param instanceValue an instance value of the data type." + NL + "\t * @return a literal representation of the instance value." + NL + "\t * @generated" + NL + "\t */" + NL + "\tString convert";
  protected final String TEXT_460 = "(";
  protected final String TEXT_461 = " instanceValue);" + NL;
  protected final String TEXT_462 = NL + "\t/**" + NL + "\t * Returns the package supported by this factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the package supported by this factory." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_463 = " get";
  protected final String TEXT_464 = "();" + NL;
  protected final String TEXT_465 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_466 = " get";
  protected final String TEXT_467 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_468 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_469 = NL + "\t@Deprecated";
  protected final String TEXT_470 = NL + "\tpublic static ";
  protected final String TEXT_471 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_472 = ".eINSTANCE;" + NL + "\t}" + NL;
  protected final String TEXT_473 = NL + "} //";
  protected final String TEXT_474 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    GenPackage genPackage = (GenPackage)((Object[])argument)[0]; GenModel genModel=genPackage.getGenModel(); /* Trick to import java.util.* without warnings */Iterator.class.getName();
    boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    if (isInterface || genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getReflectionPackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    if (isImplementation) {
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container");
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container.Dynamic");
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    stringBuffer.append(TEXT_10);
     if (!genPackage.getAllSwitchGenClasses().isEmpty()){ 
	genModel.addImport(genPackage.getUtilitiesPackageName()+"."+genPackage.getAdapterFactoryClassName());
	genModel.addImport("fr.inria.atlanmod.neo4emf.change.impl.ChangeLog");
	genModel.addImport("fr.inria.atlanmod.neo4emf.change.impl.NewObject");}
    if (!genPackage.hasJavaLangConflict() && !genPackage.hasInterfaceImplConflict() && !genPackage.getClassPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    }
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_11);
    if (isInterface) {
    stringBuffer.append(TEXT_12);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    }
    stringBuffer.append(TEXT_14);
    } else {
    stringBuffer.append(TEXT_15);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EFactoryImpl"));
    if (!genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    }
    } else {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EFactory"));
    }
    }
    stringBuffer.append(TEXT_21);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_25);
    }
    if (isImplementation && (genModel.isSuppressEMFMetaData() || genModel.isSuppressInterfaces())) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_27);
    }
    if (isInterface && genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_30);
    } else if (isInterface && !genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_33);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_34);
    if (!genPackage.getAllSwitchGenClasses().isEmpty()){
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genPackage.getAdapterFactoryClassName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getAdapterFactoryClassName());
    stringBuffer.append(TEXT_37);
    }
    stringBuffer.append(TEXT_38);
    String factoryType = genModel.isSuppressEMFMetaData() ? genPackage.getFactoryClassName() : genPackage.getImportedFactoryInterfaceName();
    stringBuffer.append(TEXT_39);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genPackage.getImportedFactoryClassName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_51);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_52);
    }
    stringBuffer.append(TEXT_53);
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(!genClass.isEObjectExtension() ? "(EObject)" : "" );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_58);
    }
    }
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_60);
    if (!genPackage.getAllGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_61);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_62);
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_64);
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_68);
    }
    }
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_70);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_73);
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_77);
    }
    }
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_79);
    }
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_82);
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genClass.getCastFromEObject());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_87);
    } else {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(genClass.getClassTypeArguments());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(genClass.getClassTypeArguments());
    stringBuffer.append(TEXT_91);
    if (genModel.isSuppressInterfaces() && !genPackage.getReflectionPackageName().equals(genPackage.getInterfacePackageName())) {
    stringBuffer.append(TEXT_92);
    }
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genPackage.getClassUniqueName(genClass));
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_98);
    }
    }
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) { String eDataType = genDataType.getQualifiedClassifierAccessor();
    stringBuffer.append(TEXT_99);
    if (genModel.useGenerics() && genDataType.isUncheckedCast() && !genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_100);
    }
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_103);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_104);
    } else {
    stringBuffer.append(TEXT_105);
    }
    stringBuffer.append(TEXT_106);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genDataType.getCreatorBody(genModel.getIndentation(stringBuffer)));
    } else if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_112);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); boolean isPrimitiveConversion = !genDataType.isPrimitiveType() && genBaseType.isPrimitiveType();
    if (genBaseType.getGenPackage() == genPackage) {
    if (isPrimitiveConversion && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_115);
    } else {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_117);
    }
    } else if (genBaseType.getGenPackage().isDataTypeConverters()) {
    if (isPrimitiveConversion && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_121);
    } else {
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_124);
    }
    } else {
    stringBuffer.append(TEXT_125);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_129);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genItemType.getObjectType().getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_133);
    }
    stringBuffer.append(TEXT_134);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_137);
    } else {
    stringBuffer.append(TEXT_138);
    }
    stringBuffer.append(TEXT_139);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_140);
    }
    if (genItemType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_142);
    } else {
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_145);
    }
    } else {
    if (genItemType.getGenPackage().isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_148);
    } else {
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_151);
    }
    }
    stringBuffer.append(TEXT_152);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genDataType.getStaticValue(null));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genDataType.getStaticValue(null));
    stringBuffer.append(TEXT_156);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_157);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { if (!genDataType.isPrimitiveType()) genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_159);
    } else {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_163);
    }
    } else {
    if (genPackage.isDataTypeConverters()) { if (!genDataType.isPrimitiveType()) genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_166);
    } else {
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_170);
    }
    }
    stringBuffer.append(TEXT_171);
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_172);
    }
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_173);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_174);
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_176);
    } else {
    stringBuffer.append(TEXT_177);
    }
    stringBuffer.append(TEXT_178);
    }
    stringBuffer.append(TEXT_179);
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_180);
    }
    stringBuffer.append(TEXT_181);
    } else if (genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_183);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_185);
    } else if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_189);
    } else {
    stringBuffer.append(TEXT_190);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_192);
    }
    stringBuffer.append(TEXT_193);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_194);
    }
    stringBuffer.append(TEXT_195);
    }
    stringBuffer.append(TEXT_196);
    if (!genPackage.isDataTypeConverters() && genModel.useGenerics() && genDataType.isUncheckedCast() && !genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_197);
    }
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_201);
    if (genDataType instanceof GenEnum) {
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_203);
    } else {
    stringBuffer.append(TEXT_204);
    stringBuffer.append(((GenEnum)genDataType).getImportedInstanceClassName());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(((GenEnum)genDataType).getImportedInstanceClassName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_207);
    }
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_208);
    if (!genDataType.getObjectInstanceClassName().equals(genBaseType.getObjectInstanceClassName())) {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_210);
    }
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_213);
    } else {
    stringBuffer.append(TEXT_214);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_216);
    }
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_218);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_220);
    } else {
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genItemType.getObjectType().getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_224);
    }
    stringBuffer.append(TEXT_225);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_228);
    } else {
    stringBuffer.append(TEXT_229);
    }
    stringBuffer.append(TEXT_230);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_231);
    }
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_234);
    } else {
    stringBuffer.append(TEXT_235);
    if (!genItemType.isObjectType()) {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_237);
    }
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_239);
    }
    stringBuffer.append(TEXT_240);
    }
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (genPackage.isDataTypeConverters()) {
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_243);
    } else {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_245);
    }
    } else {
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_247);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_248);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_249);
    if (!genDataType.isObjectType() && !genDataType.getObjectInstanceClassName().equals(genMemberType.getObjectInstanceClassName())) {
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_251);
    }
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_254);
    } else {
    stringBuffer.append(TEXT_255);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_261);
    }
    stringBuffer.append(TEXT_262);
    }
    } else if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_264);
    } else if (genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_265);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_267);
    }
    stringBuffer.append(TEXT_268);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_270);
    } else {
    stringBuffer.append(TEXT_271);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_273);
    }
    stringBuffer.append(TEXT_274);
    }
    stringBuffer.append(TEXT_275);
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) { String eDataType = genDataType.getQualifiedClassifierAccessor();
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_278);
    if (genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_279);
    } else {
    stringBuffer.append(TEXT_280);
    }
    stringBuffer.append(TEXT_281);
    if (genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genDataType.getConverterBody(genModel.getIndentation(stringBuffer)));
    } else if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_283);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); boolean isPrimitiveConversion = !genDataType.isPrimitiveType() && genBaseType.isPrimitiveType();
    if (genBaseType.getGenPackage() == genPackage) {
    if (isPrimitiveConversion) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_285);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genBaseType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_287);
    }
    stringBuffer.append(TEXT_288);
    } else {
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_290);
    }
    } else if (genBaseType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_293);
    } else {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_296);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_299);
    String item; if (!genModel.useGenerics()) { item = "i.next()"; 
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_301);
    } else { item = "item";
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_303);
    }
    stringBuffer.append(TEXT_304);
    if (genItemType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_307);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_308);
    } else {
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_312);
    }
    } else {
    if (genItemType.getGenPackage().isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_317);
    } else {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_321);
    }
    }
    stringBuffer.append(TEXT_322);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_323);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_325);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) {
    if (genMemberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_327);
    } else if (genMemberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genMemberType.getObjectType().getImportedInstanceClassName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genMemberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_331);
    } else {
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genMemberType.getObjectType().getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_334);
    }
    } else {
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_337);
    }
    } else {
    if (genMemberType.getGenPackage().isDataTypeConverters()) { genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_341);
    } else {
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_344);
    }
    }
    stringBuffer.append(TEXT_345);
    }
    } else {
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_346);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_348);
    } else {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_351);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_353);
    } else {
    stringBuffer.append(TEXT_354);
    }
    stringBuffer.append(TEXT_355);
    }
    } else {
    if (genMemberType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_358);
    } else {
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_361);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_363);
    } else {
    stringBuffer.append(TEXT_364);
    }
    stringBuffer.append(TEXT_365);
    }
    }
    stringBuffer.append(TEXT_366);
    }
    }
    stringBuffer.append(TEXT_367);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_368);
    } else if (genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_369);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_371);
    } else if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_372);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_374);
    } else {
    stringBuffer.append(TEXT_375);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_376);
    }
    stringBuffer.append(TEXT_377);
    }
    stringBuffer.append(TEXT_378);
    if (genModel.useGenerics() && (genDataType.getItemType() != null || genDataType.isUncheckedCast()) && (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody())) {
    stringBuffer.append(TEXT_379);
    }
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_382);
    if (genDataType instanceof GenEnum) {
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_385);
    } else {
    stringBuffer.append(TEXT_386);
    }
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_389);
    } else {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_392);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_395);
    } else { final String singleWildcard = genModel.useGenerics() ? "<?>" : "";
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_400);
    String item; if (!genModel.useGenerics()) { item = "i.next()"; 
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_402);
    } else { item = "item";
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_404);
    }
    stringBuffer.append(TEXT_405);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_409);
    } else {
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_413);
    }
    stringBuffer.append(TEXT_414);
    }
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_417);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_419);
    }
    stringBuffer.append(TEXT_420);
    } else {
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_422);
    }
    } else {
    stringBuffer.append(TEXT_423);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_425);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_428);
    } else {
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_431);
    }
    stringBuffer.append(TEXT_432);
    }
    stringBuffer.append(TEXT_433);
    }
    } else if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_435);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_436);
    }
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_438);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_440);
    }
    stringBuffer.append(TEXT_441);
    } else {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_444);
    }
    } else if (genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_445);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_447);
    } else {
    stringBuffer.append(TEXT_448);
    }
    stringBuffer.append(TEXT_449);
    }
    }
    } else {
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (genClass.hasFactoryInterfaceCreateMethod()) {
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_454);
    }
    }
    if (genPackage.isDataTypeConverters()) {
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_461);
    }
    }
    }
    }
    if (!isImplementation && !genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_464);
    } else if (isImplementation) {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_468);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_469);
    }
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_472);
    }
    stringBuffer.append(TEXT_473);
    stringBuffer.append(isInterface ? genPackage.getFactoryInterfaceName() : genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_474);
    return stringBuffer.toString();
  }
}
