class TranslatorNote {
	int degree;
	int duration;
	int octave;
	boolean dotted;
	
	static char[] durationArray = {'o', 'x', 't', 's', 'i', 'q', 'h', 'w'};
								 /* 0    1    2    3    4    5    6    7 */
	static int[] degreeArray = {0, 1, 3, 5, 6, 8, 10, 12};
	
	TranslatorNote(int degree, int duration) {
		this.degree = degree;
		this.duration = duration;
		dotted = false;
		octave = 0;
	}
	
	TranslatorNote(boolean high, int duration) {
		if ( high ) octave = 1;
		else octave = -1;
	}
	
	void dot() {
		dotted = true;
	}
	
	void sharp() {
		++degree;
	}
	
	void flat() {
		--degree;
	}
	
	void lengthen() {
		++duration;
	}
	
	void octaveUp() {
		++octave;
	}
	
	void octaveDown() {
		--octave;
	}
	
	void setDegree(int degree) {
		this.degree = degree;
	}
	
	@Override
	public String toString() {
		int absoluteDegree = (12*octave)+degreeArray[degree];
		String note = "<" + absoluteDegree + ">" + durationArray[duration];
		if ( dotted ) note += '.';
		note += ' ';
		return note;
	}
	
}