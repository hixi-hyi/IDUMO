require "rexml/document"
include REXML

IMPORT_LIST = <<EOI
import com.hixi_hyi.idumo.console.*;
import com.hixi_hyi.idumo.console.core.exec.*;
import com.hixi_hyi.idumo.console.provider.*;
import com.hixi_hyi.idumo.console.handler.*;
import com.hixi_hyi.idumo.console.receiptor.*;
import com.hixi_hyi.idumo.core.*;
import com.hixi_hyi.idumo.core.exec.*;
import com.hixi_hyi.idumo.common.provider.*;
import com.hixi_hyi.idumo.common.handler.*;
import com.hixi_hyi.idumo.common.receiptor.*;
EOI

def gen_package_statement(name)
 return "package #{name};"
end

def gen_class(node)
 s = ""
 s += "public class #{node.attributes['name']}Main extends AbstractConsoleMain {\n"
 s += gen_idumo_init(node.attributes['name'])
 s += gen_main(node.attributes['name'])
 s += "}\n"
 return s
end

def gen_main(name)
 s = ""
 s += "  public static void main(String[] args){ \n"
 s += "    #{name}Main main = new #{name}Main(); \n"
 s += "    main.exec(); \n"
 s += "  }\n"
 return s
end

def gen_create_component(node)
 s = ""
 s += "class #{node.attributes['name']}Component extends AbstractExecutionComponent {\n"
 s += gen_idumo_flow_chart(node)
 s += gen_idumo_prepare(node.elements['executions'])
 s += "}\n"
 return s
end

def gen_idumo_init(componentname)
 s = ""
 s += "  @Override\n"
 s += "  public void init() {\n"
 s += "    setExecutionWithComponent(new #{componentname}Component());\n"
 s += "  }\n"
 return s
end

def parse_options(node)
 s = "\n"
 node.elements.each("use"){|n|
   s += gen_device_use(n) + "\n"
 }
 return s
end

def gen_item_instance(node)
 s = ""
 s += "    #{node.attributes['class']} #{node.attributes['name']} = new #{node.attributes['class']}(#{node.attributes['init']});\n"
 if node.attributes['option'] then
   node.attributes['option'].split(",").each{|opt|
     s += "    #{node.attributes['name']}.setOption(#{opt});\n"
   }
 end
 s += "    add(#{node.attributes['name']});";
 s += "\n"
 return s
end

def gen_connection(node)
 return "    connect(#{node.attributes['src']}, #{node.attributes['sink']});\n"
end


def gen_idumo_flow_chart(node)
 s = ""
 s += "  @Override\n"
 s += "  public void onIdumoMakeFlowChart() throws IdumoException {\n"
 node.elements['items'].elements.each("item"){|n|
   s += gen_item_instance(n) 
 }
 s += "\n"
 node.elements['connections'].elements.each("connect"){|n|
   s += gen_connection(n)
 }
 s += "  }\n"
 return s
end


def gen_idumo_prepare(node)
 s = ""
 s += "  @Override\n"
 s += "  public void onIdumoPrepare() {\n"
 s += "    setLoopCount(#{node.elements['loop'].attributes['count']});\n"
 s += "    setSleepTime(#{node.elements['sleep'].attributes['time']});\n"
 s += "  }\n"
 return s
end

def parse_and_generate(node)
 puts gen_package_statement(node.attributes['package'])
 puts IMPORT_LIST
 puts gen_class(node)
 puts gen_create_component(node)
end

ARGV.each{|argv|
 doc = Document.new File.new(argv)
 parse_and_generate(doc.elements['idumo'])
}
