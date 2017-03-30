package shared;
/**
 * Diese Klasse hilft Html Code zu generieren.
 * @author fluktuid
 *
 */
public class HtmlBuilder {
	StringBuilder builder = new StringBuilder();
	
	public HtmlBuilder startTable() {
		builder.append("<table>");
		return this;
	}
	
	public HtmlBuilder endTable() {
		builder.append("</table>");
		return this;
	}
	
	public HtmlBuilder startTd() {
		builder.append("<td>");
		return this;
	}
	
	public HtmlBuilder endTd() {
		builder.append("</td>");
		return this;
	}
	
	public HtmlBuilder startTr() {
		builder.append("<tr>");
		return this;
	}
	
	public HtmlBuilder endTr() {
		builder.append("</tr>");
		return this;
	}
	
	public HtmlBuilder text(String text) {
		builder.append(text);
		return this;
	}
	
	public String build() {
		return builder.toString();
	}
	
	public HtmlBuilder input(String name,String type, String classe){
		name = (name==null)? "name":name;
		type = (type==null)? "Button":type;
		classe = (classe==null)?"text-line":classe;
		builder.append("<input name=\""+name+"\" type=\""+type+"\" class=\""+classe+"\">");
		return this;
	}
	
	public HtmlBuilder input(String name,String type, String classe, double step){
		name = (name==null)? "name":name;
		type = (type==null)? "Button":type;
		classe = (classe==null)?"text-line":classe;
		builder.append("<input name=\""+name+"\" type=\""+type+"\" class=\""+classe+"\" step=\""+step+"\">");
		return this;
	}
}
