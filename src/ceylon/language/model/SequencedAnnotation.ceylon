"An annotation that may occur multiple times
 at a single program element."
shared interface SequencedAnnotation<out Value, in ProgramElement>
        of Value
        satisfies ConstrainedAnnotation<Value,Value[],ProgramElement>
        given Value satisfies Annotation<Value>
        given ProgramElement satisfies Annotated {}


shared Value[] sequencedAnnotations<Value,ProgramElement>(
            ClassOrInterface<SequencedAnnotation<Value,ProgramElement>> annotationType,
            ProgramElement programElement)
        given Value satisfies SequencedAnnotation<Value,ProgramElement>
        given ProgramElement satisfies Annotated { 
    return annotations<Value,Value[],ProgramElement>(annotationType, programElement);
}