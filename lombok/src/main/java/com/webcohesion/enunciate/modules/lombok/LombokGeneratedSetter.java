package com.webcohesion.enunciate.modules.lombok;

import com.webcohesion.enunciate.javac.decorations.DecoratedProcessingEnvironment;
import com.webcohesion.enunciate.javac.decorations.SourcePosition;
import com.webcohesion.enunciate.javac.decorations.adaptors.ExecutableElementAdaptor;
import com.webcohesion.enunciate.javac.decorations.element.ElementUtils;

import javax.lang.model.element.*;
import javax.lang.model.type.*;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ryan Heaton
 */
public class LombokGeneratedSetter implements ExecutableElementAdaptor {

  private final VariableElement var;
  private final DecoratedProcessingEnvironment env;
  private final Name simpleName;

  public LombokGeneratedSetter(VariableElement var, DecoratedProcessingEnvironment env) {
    this.var = var;
    this.env = env;
    this.simpleName = this.env.getElementUtils().getName("set" + ElementUtils.capitalize(this.var.getSimpleName().toString()));
  }

  @Override
  public List<? extends TypeParameterElement> getTypeParameters() {
    return Collections.emptyList();
  }

  @Override
  public TypeMirror getReturnType() {
    return this.env.getTypeUtils().getNoType(TypeKind.VOID);
  }

  @Override
  public List<? extends VariableElement> getParameters() {
    return Collections.singletonList(this.var);
  }

  @Override
  public boolean isVarArgs() {
    return false;
  }

  @Override
  public List<? extends TypeMirror> getThrownTypes() {
    return Collections.emptyList();
  }

  @Override
  public AnnotationValue getDefaultValue() {
    return null;
  }

  @Override
  public Name getSimpleName() {
    return simpleName;
  }

  @Override
  public TypeMirror asType() {
    return new Type();
  }

  @Override
  public ElementKind getKind() {
    return ElementKind.METHOD;
  }

  @Override
  public List<? extends AnnotationMirror> getAnnotationMirrors() {
    return this.var.getAnnotationMirrors();
  }

  @Override
  public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
    return this.var.getAnnotation(annotationType);
  }

  @Override
  public Set<Modifier> getModifiers() {
    return EnumSet.of(Modifier.PUBLIC);
  }

  @Override
  public Element getEnclosingElement() {
    return this.var.getEnclosingElement();
  }

  @Override
  public List<? extends Element> getEnclosedElements() {
    return Collections.emptyList();
  }

  @Override
  public <R, P> R accept(ElementVisitor<R, P> v, P p) {
    return v.visitExecutable(this, p);
  }

  @Override
  public boolean overrides(ExecutableElement overridden, TypeElement scope) {
    return (overridden instanceof LombokGeneratedSetter) && (this.env.getElementUtils().hides(this.var, ((LombokGeneratedSetter) overridden).var));
  }

  @Override
  public String getDocComment() {
    String docComment = this.env.getElementUtils().getDocComment(this.var);
    if (docComment != null && !docComment.trim().isEmpty()) {
      return docComment + "\n@param " + this.var.getSimpleName().toString() + ' ' + docComment;
    }
    return null;
  }

  @Override
  public boolean isDeprecated() {
    return this.env.getElementUtils().isDeprecated(this.var);
  }

  @Override
  public boolean isOverriddenBy(ExecutableElement overrider, TypeElement type) {
    return (overrider instanceof LombokGeneratedSetter) && ((LombokGeneratedSetter) overrider).overrides(this, type);
  }

  @Override
  public PackageElement getPackage() {
    return this.env.getElementUtils().getPackageOf(this.var);
  }

  @Override
  public List<? extends AnnotationMirror> getAllAnnotationMirrors() {
    return this.env.getElementUtils().getAllAnnotationMirrors(this.var);
  }

  @Override
  public boolean hides(Element hidden) {
    return false;
  }

  @Override
  public boolean isHiddenBy(Element hider) {
    return false;
  }

  @Override
  public SourcePosition getSourcePosition() {
    return this.env.findSourcePosition(this.var);
  }

  @Override
  public TypeMirror getReceiverType() {
    return env.getTypeUtils().getNoType(TypeKind.NONE);
  }

  @Override
  public boolean isDefault() {
    return false;
  }

  @Override
  public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationType) {
    return this.var.getAnnotationsByType(annotationType);
  }

  private class Type implements ExecutableType {

    @Override
    public List<? extends TypeVariable> getTypeVariables() {
      return Collections.emptyList();
    }

    @Override
    public TypeMirror getReturnType() {
      return env.getTypeUtils().getNoType(TypeKind.VOID);
    }

    @Override
    public List<? extends TypeMirror> getParameterTypes() {
      return Collections.singletonList(var.asType());
    }

    @Override
    public List<? extends TypeMirror> getThrownTypes() {
      return Collections.emptyList();
    }

    @Override
    public TypeKind getKind() {
      return TypeKind.EXECUTABLE;
    }

    @Override
    public TypeMirror getReceiverType() {
      return env.getTypeUtils().getNoType(TypeKind.NONE);
    }

    @Override
    public List<? extends AnnotationMirror> getAnnotationMirrors() {
      return var.getAnnotationMirrors();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
      return var.getAnnotation(annotationType);
    }

    @Override
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationType) {
      return var.getAnnotationsByType(annotationType);
    }

    @Override
    public <R, P> R accept(TypeVisitor<R, P> v, P p) {
      return v.visitExecutable(this, p);
    }
  }
}
