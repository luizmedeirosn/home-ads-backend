package com.luizmedeirosn.homeads.configs;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.luizmedeirosn.homeads.entities.Ad;
import com.luizmedeirosn.homeads.entities.AdImage;
import com.luizmedeirosn.homeads.entities.User;
import com.luizmedeirosn.homeads.entities.enums.AdCategoryEnum;
import com.luizmedeirosn.homeads.entities.enums.UserRole;
import com.luizmedeirosn.homeads.repositories.AdRepository;
import com.luizmedeirosn.homeads.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

	private final AdRepository adRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@SuppressWarnings("java:S6437")
	public void run(String... args) throws Exception {
		User admin = User.builder()
				.username("admin")
				.email("admin@homeads.com")
				.password(passwordEncoder.encode("admin"))
				.role(UserRole.ADMIN)
				.build();
		User user = User.builder()
				.username("user")
				.email("user@homeads.com")
				.password(passwordEncoder.encode("user"))
				.role(UserRole.USER)
				.build();

		userRepository.saveAll(Arrays.asList(admin, user));

		initAds();
	}

	private void initAds() throws SQLException, IOException {
		Ad ad1 = new Ad(
				null,
				"Combo toalhas de banho",
				"Envolva-se em conforto após o banho com nosso combo de toalhas macias e absorventes, proporcionando uma experiência de spa no seu banheiro.",
				new BigDecimal("32.9"), 3, AdCategoryEnum.BED_AND_BATH, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad2 = new Ad(
				null,
				"Garrafa de café 1.8 litros",
				"Desfrute de café quente por mais tempo com nossa garrafa térmica de 1.8 litros, mantendo sua bebida preferida na temperatura ideal.",
				new BigDecimal("54.49"), 4, AdCategoryEnum.BED_AND_BATH, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad3 = new Ad(
				null,
				"Lençol de casal com bordado",
				"Adicione um toque de elegância ao seu quarto com nosso lençol de casal bordado, combinando estilo e conforto para uma noite de sono tranquila.",
				new BigDecimal("289.99"), 4, AdCategoryEnum.BED_AND_BATH, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad4 = new Ad(
				null,
				"Toalha de Mesa",
				"Transforme suas refeições com nossa toalha de mesa elegante, proporcionando um toque de sofisticação à sua sala de jantar.",
				new BigDecimal("61.29"), 5, AdCategoryEnum.BED_AND_BATH, Instant.now(), null,
				userRepository.findById(1L).get());

		Ad ad5 = new Ad(
				null,
				"Ar condicionado Eletrolux",
				"Equipado com um sistema de refrigeração poderoso, o ar condicionado é capaz de remover o calor excessivo do ar, promovendo uma sensação de frescor e bem-estar.",
				new BigDecimal("2299.9"), 5, AdCategoryEnum.APPLIANCES, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad6 = new Ad(
				null,
				"Máquina de café expresso",
				"Desfrute do café perfeito com nossa máquina expresso: design elegante, tecnologia avançada e extração ideal para um sabor e aroma excepcionais. Uma experiência de barista no conforto da sua casa.",
				new BigDecimal("1389.99"), 5, AdCategoryEnum.APPLIANCES, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad7 = new Ad(
				null,
				"Ventilador de parede",
				"Mantenha sua casa fresca e arejada com nosso ventilador de parede silencioso e potente, oferecendo conforto em dias quentes de maneira discreta e eficiente.",
				new BigDecimal("210.18"), 4, AdCategoryEnum.APPLIANCES, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad8 = new Ad(
				null,
				"Máquina de lavar",
				"Eficiência em cada ciclo - nossa máquina de lavar proporciona limpeza poderosa e economia de água, tornando a tarefa de lavar roupa mais simples e sustentável.",
				new BigDecimal("1030.55"), 5, AdCategoryEnum.APPLIANCES, Instant.now(), null,
				userRepository.findById(1L).get());

		Ad ad9 = new Ad(
				null,
				"Armário de 7 portas",
				"Organize seus pertences com estilo em nosso armário espaçoso de 7 portas, oferecendo praticidade e design moderno para o seu espaço.",
				new BigDecimal("452.29"), 3, AdCategoryEnum.FURNITURE, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad10 = new Ad(
				null,
				"Cama de casal",
				"Descanse com conforto e estilo em nossa cama de casal, criando um refúgio acolhedor no seu quarto.",
				new BigDecimal("299.7"), 5, AdCategoryEnum.FURNITURE, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad11 = new Ad(
				null,
				"Poltrona reclinável",
				"Relaxe em grande estilo com nossa poltrona reclinável, combinando conforto ergonômico e design contemporâneo.",
				new BigDecimal("784.96"), 5, AdCategoryEnum.FURNITURE, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad12 = new Ad(
				null,
				"Sofá de 3 lugares",
				"Reúna a família em torno do conforto do nosso sofá espaçoso, criando um espaço acolhedor para momentos especiais.",
				new BigDecimal("499.99"), 4, AdCategoryEnum.FURNITURE, Instant.now(), null,
				userRepository.findById(1L).get());

		Ad ad13 = new Ad(
				null,
				"Esfregão para pisos lisos",
				"Facilite a limpeza da sua casa com nosso esfregão eficiente, projetado para lidar com os desafios diários de manter seus pisos impecáveis.",
				new BigDecimal("29.99"), 4, AdCategoryEnum.TOOLS, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad14 = new Ad(
				null,
				"Kit de Ferramentas",
				"Esteja preparado para qualquer tarefa com nosso kit de ferramentas abrangente, fornecendo as ferramentas essenciais para projetos em casa.",
				new BigDecimal("99.9"), 5, AdCategoryEnum.TOOLS, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad15 = new Ad(
				null,
				"Pá de Bico",
				"Limpeza eficaz em cantos e fendas com nossa pá de bico, garantindo que nenhum resíduo escape à sua atenção.",
				new BigDecimal("39.9"), 5, AdCategoryEnum.TOOLS, Instant.now(), null,
				userRepository.findById(1L).get());
		Ad ad16 = new Ad(
				null,
				"Vassoura de Pelo 60cm",
				"Mantenha seus espaços impecáveis com nossa vassoura de pelo de 60cm, oferecendo uma varredura eficiente e fácil.",
				new BigDecimal("18.55"), 4, AdCategoryEnum.TOOLS, Instant.now(), null,
				userRepository.findById(1L).get());

		List<Ad> ads = adRepository.saveAll(Arrays.asList(
				ad1, ad2, ad3, ad4, ad5, ad6, ad7, ad8, ad9, ad10, ad11, ad12, ad13, ad14, ad15, ad16));

		final String PICTURES_FOLDER_PATH = "/home/luizmedeirosn/Downloads/home-ads-backend/src/main/java/com/luizmedeirosn/homeads/configs/data";
		File picturesFolder = new File(PICTURES_FOLDER_PATH);
		File[] imagesArray = picturesFolder.listFiles(File::isFile);

		List<File> imagesList = new ArrayList<>(Arrays.asList(imagesArray));
		imagesList.sort((f1, f2) -> Integer.parseInt(f1.getName().split("-")[0])
				- Integer.parseInt(f2.getName().split("-")[0]));

		for (int i = 0; i < 16; i++) {
			Ad ad = ads.get(i);
			File image = imagesList.get(i);
			Blob data = new SerialBlob(Files.readAllBytes(Paths.get(image.getAbsolutePath())));
			String imageName = "image/" + image.getName().split("\\.")[1];

			AdImage adImage = new AdImage(
					null, image.getName(), imageName, data, ad);

			ad.setImage(adImage);
			adRepository.save(ad);
		}

	}
}
