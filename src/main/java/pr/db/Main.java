package pr.db;

import org.slf4j.Logger;

public class Main {
	private static final Logger LOG = Tools.LOG;

	public static void main(String[] args) {
		LOG.info(args.length + "");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			LOG.info("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			return;
		}

		if(args.length > 0) {
			try {
				String command = args[0].toLowerCase();
				if(command.equals("create")) {
					Create cr = new Create();
					if(args.length == 1) {
						cr.create();
					} else {
						cr.create(args[1], args[2], args[3], args[4]);
					}
				} else if(command.equals("clear")) {
					Clear cl = new Clear();
					if(args.length == 1) {
						cl.clear();
					} else {
						cl.clear(args[1], args[2], args[3], args[4]);
					}
				}
				LOG.info("DONE!");
			} catch (Exception e) {
				LOG.error("ERROR!");
				e.printStackTrace();
			}
		}
	}
}