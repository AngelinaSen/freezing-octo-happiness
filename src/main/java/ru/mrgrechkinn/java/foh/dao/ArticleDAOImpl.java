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

    
	public static final String fileName = "C:\\"; 
	private File file;

	public ArticleDAOImpl(Article article) {
		file = new File(fileName + article.getId() + ".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public boolean save(Article article) {

		try {
			FileWriter writer = new FileWriter(file, true);

			writer.write("Id - " + String.valueOf(article.getId()) + "\n");
			writer.write("" + "\n");
			writer.write(article.getContent() + "\n");
			writer.write("" + "\n");
			writer.write("" + "\n");
			writer.write(article.getSubject() + "\n");

			writer.close();

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(Article article) {

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
				if (!article.getContent().equals(list.get(i))) {
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

			Object[] arr = new Object[list.size()];
			
			while (reader.readLine() != null) {
				String line;
				if ((line = reader.readLine()) != null) {
					list.add(line);
				}
			}
			
				
			for (int i = 1; i < list.size();) {
				if (Long.parseLong(list.get(i - 1)) == id) {
					//System.out.println(list.get(i));
					arr[i] = list.get(i);
				} else {
					//System.out.print("");
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
			Object[] arr = new Object[1];
			try {
				while (reader.readLine() != null) {
					list.add(reader.readLine());
				}
				arr[0] = list;

				//for (String s : list) {
					//System.out.println(s);
					
				//}
				reader.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean save(Entity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Entity userEntity) {
		// TODO Auto-generated method stub
		return false;
	}

}
