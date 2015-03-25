package supton.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import supton.dao.ITerminalDao;
import supton.dao.IVaultlogDao;
import supton.entity.Terminal;
import supton.entity.Vaultlog;


@Service
public class TerminalServiceImpl implements ITerminalService {

	@Autowired
	private ITerminalDao terminalDao;

	public void save(Terminal transientInstance) {
		terminalDao.save(transientInstance);
	}

	public void delete(Terminal persistentInstance) {
		terminalDao.delete(persistentInstance);
	}

	public Terminal findById(String id) {
		return terminalDao.findById(id);
	}
	
}