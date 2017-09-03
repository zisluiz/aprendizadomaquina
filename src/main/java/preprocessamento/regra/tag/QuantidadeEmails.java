package preprocessamento.regra.tag;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.select.Elements;

import preprocessamento.model.Pagina;

public class QuantidadeEmails implements RegraTag {

	@Override
	public String getNome() {
		return "#qtde_emails";
	}

	@Override
	public Integer getValor(Elements elements, Pagina pagina) {
        Pattern pattern = Pattern.compile("([a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(elements.outerHtml());
        Set<String> emails = new HashSet<>();
        while (matcher.find()) {
        	emails.add(matcher.group(1));
        }
        
        return emails.size();
	}
}