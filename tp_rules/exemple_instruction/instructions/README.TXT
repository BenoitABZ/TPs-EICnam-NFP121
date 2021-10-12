#verbose=true

bean.id.1=e
e.class=exemple_instruction.instructions.InstructionsTests$Entier
e.property.1=value
e.property.1.param.1=2

bean.id.2=m
m.class=exemple_instruction.instructions.Memory

bean.id.3=inf
inf.class=exemple_instruction.instructions.InstructionsTests$Inf
inf.property.1=operand
inf.property.1.param.1=10
inf.property.2=memory
inf.property.2.param.1=m

bean.id.4=inc
inc.class=exemple_instruction.instructions.InstructionsTests$Inc

bean.id.5=factoriel
factoriel.class=exemple_instruction.instructions.InstructionsTests$Factoriel
factoriel.property.1=trace
factoriel.property.1.param.1=true

bean.id.6=seq
seq.class=exemple_instruction.instructions.Sequence
seq.property.1=instruction1
seq.property.1.param.1=inc
seq.property.2=instruction2
seq.property.2.param.1=inc

bean.id.7=while
while.class=exemple_instruction.instructions.While
while.property.1=specification
while.property.1.param.1=inf
while.property.2=instruction
while.property.2.param.1=inc
#while.property.2.param.1=inc4

bean.id.8=ifThen
ifThen.class=exemple_instruction.instructions.IfThen
ifThen.property.1=specification
ifThen.property.1.param.1=inf
ifThen.property.2=instruction
ifThen.property.2.param.1=inc
#ifThen.property.2.param.1=inc4

bean.id.9=inc4
inc4.class=exemple_instruction.instructions.MacroInstruction
inc4.property.1=instructions
inc4.property.1.param.1=inc inc inc inc

bean.id.10=inc8
inc8.class=exemple_instruction.instructions.MacroInstruction
inc8.property.1=instructions
inc8.property.1.param.1=inc4 inc4

bean.id.11=maybe
maybe.class=question1.MAYBE

bean.id.12=composite
composite.class=exemple_instruction.instructions.IfThen
composite.property.1=specification
composite.property.1.param.1=maybe
composite.property.2=instruction
#composite.property.2.param.1=while
composite.property.2.param.1=seq2
#composite.property.2.param.1=inc8


bean.id.13=while2
while2.class=exemple_instruction.instructions.While
while2.property.1=specification
while2.property.1.param.1=inf1000
while2.property.2=instruction
while2.property.2.param.1=seq2
while2.property.3=trace
while2.property.3.param.1=true

bean.id.14=seq2
seq2.class=exemple_instruction.instructions.Sequence
seq2.property.1=instruction1
seq2.property.1.param.1=factoriel
seq2.property.2=instruction2
seq2.property.2.param.1=factoriel

bean.id.15=debug
debug.class=exemple_instruction.instructions.Debug

bean.id.16=inf1000
inf1000.class=exemple_instruction.instructions.InstructionsTests$Inf
inf1000.property.1=operand
inf1000.property.1.param.1=100
inf1000.property.2=memory
inf1000.property.2.param.1=m
