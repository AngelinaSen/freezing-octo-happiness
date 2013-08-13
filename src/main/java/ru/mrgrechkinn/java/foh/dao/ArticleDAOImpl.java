package ru.mrgrechkinn.java.foh.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.mrgrechkinn.java.foh.model.Article;
import ru.mrgrechkinn.java.foh.model.Entity;

public class ArticleDAOImpl implements ArticleDAO {

	public static final String fileName = "C:\\123.txt";
	private File file;

	public ArticleDAOImpl() {
		file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean save(Entity entity) {

		try {
			FileWriter writer = new FileWriter(file, true);

			writer.write("Id - " + String.valueOf(Article.getId()) + "\n");
			writer.write("" + "\n");
			writer.write(Article.getContent() + "\n");
			writer.write("" + "\n");
			writer.write("" + "\n");
			writer.write(Article.getSubject() + "\n");

			writer.close();

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(Entity userEntity) {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(file));

			List<String> list = new ArrayList<String>();
			List<String> listTwo = new ArrayList<String>();

			try {
				while (reader.readLine() != null) {
					String line;
					try {
						line = reader.readLine();
						if (line != null) {
							list.add(line);
						}
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			for (int i = 6; i < list.size();) {
				if (!Article.getId().equals(list.get(i))) {
					//listTwo.add(list.get(i - 1));
					listTwo.add(list.get(i));
					//listTwo.add(list.get(i + 1));
					//listTwo.add(list.get(i + 2));
				}
				i ++;//= 4;
			}

			try {
				FileWriter writer = new FileWriter(file);

				for (int i = 0; i < listTwo.size(); i++) {
					writer.write(listTwo.get(i) + "\n");
				}
				writer.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Entity getArticleById(long id) {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(file));

			List<String> list = new ArrayList<String>();

			while (reader.readLine() != null) {
				String line;
				if ((line = reader.readLine()) != null) {
					list.add(line);
				}
			}

			for (int i = 1; i < list.size();) {
				if (Long.parseLong(list.get(i - 1)) == id) {
					System.out.println(list.get(i));
				} else {
					System.out.print("");
				}
				i ++;
			}

			reader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Entity> getAllArticles() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			List<String> list = new ArrayList<String>();

			try {
				while (reader.readLine() != null) {
					list.add(reader.readLine());
				}

				for (String s : list) {
					System.out.println(s);
				}
				reader.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		return null;
	}

}
