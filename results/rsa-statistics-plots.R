require(ggplot2)
require(gridExtra)
require(dplyr)

setwd("/home/joao/projects/master-degree/rsa/results")

bruteData = read.csv(file = "results-brute.csv", sep = ";")
pollardData = read.csv(file = "results-pollard.csv", sep = ";")
keysData = read.csv(file = "keys.csv", sep = ";")

keysData$gen_key = keysData$gen_key / 1000
keysData$encrypt = keysData$encrypt / 1000
keysData$decrypt = keysData$decrypt / 1000

bruteData = bruteData %>% 
    group_by(key_size) %>% 
    summarise(
        generate_keys_mean = mean(generate_keys) / 1000,
        encrypt_mean = mean(encrypt) / 1000,
        decrypt_mean = mean(decrypt) / 1000,
        brute_force_mean = mean(brute_force) / 1000
    )

pollardData = pollardData %>% 
    group_by(key_size) %>% 
    summarise(
        generate_keys_mean = mean(generate_keys) / 1000,
        encrypt_mean = mean(encrypt) / 1000,
        decrypt_mean = mean(decrypt) / 1000,
        brute_force_mean = mean(brute_force) / 1000
    )

allDataMerged = merge(data.frame(pollardData, row.names=NULL), data.frame(bruteData, row.names=NULL), by = "key_size", all = TRUE, suffixes = c("_pollard", "_brute"))

if(plotType == "all_brute") {
    gg <- ggplot(bruteData, aes(key_size))
    gg <- gg + ggtitle("All Brute Force")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = generate_keys_mean, color = "Generate Keys"))
    gg <- gg + geom_line(aes(y = encrypt_mean, color = "Encrypt"))
    gg <- gg + geom_line(aes(y = decrypt_mean, color = "Decrypt"))
    gg <- gg + geom_line(aes(y = brute_force_mean, color = "Brute"))
    gg
} else if (plotType == "all_pollard") {
    gg <- ggplot(pollardData, aes(key_size))
    gg <- gg + ggtitle("All Pollard Force")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = generate_keys_mean, color = "Generate Keys"))
    gg <- gg + geom_line(aes(y = encrypt_mean, color = "Encrypt"))
    gg <- gg + geom_line(aes(y = decrypt_mean, color = "Decrypt"))
    gg <- gg + geom_line(aes(y = brute_force_mean, color = "Pollard"))
    gg
} else if (plotType == "all_force") {
    gg <- ggplot(allDataMerged, aes(key_size))
    gg <- gg + ggtitle("Break Force Times")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = brute_force_mean_brute, color = "Brute"))
    gg <- gg + geom_line(aes(y = brute_force_mean_pollard, color = "Pollard"))
    gg
} else if (plotType == "all_generate_keys") {
    gg <- ggplot(allDataMerged, aes(key_size))
    gg <- gg + ggtitle("All Generate Keys")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = generate_keys_mean_brute, color = "Brute"))
    gg <- gg + geom_line(aes(y = generate_keys_mean_pollard, color = "Pollard"))
    gg
} else if (plotType == "all_encrypt") {
    gg <- ggplot(allDataMerged, aes(key_size))
    gg <- gg + ggtitle("All Encrypt")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = encrypt_mean_brute, color = "Brute"))
    gg <- gg + geom_line(aes(y = encrypt_mean_pollard, color = "Pollard"))
    gg
} else if (plotType == "all_decrypt") {
    gg <- ggplot(allDataMerged, aes(key_size))
    gg <- gg + ggtitle("All Decrypt")
    gg <- gg + xlab("Key size")
    gg <- gg + ylab("Time (s)")
    gg <- gg + geom_line(aes(y = decrypt_mean_brute, color = "Brute"))
    gg <- gg + geom_line(aes(y = decrypt_mean_pollard, color = "Pollard"))
    gg
} else if (plotType = "all_rsa") {
    #RSA
    gg1 <- ggplot(keysData, aes(key_size))
    gg1 <- gg1 + ggtitle("1) RSA")
    gg1 <- gg1 + xlab("Tamanho da chave (bits)")
    gg1 <- gg1 + ylab("Tempo (s)")
    gg1 <- gg1 + scale_y_continuous(breaks = round(seq(0, 10, by = 1), 1))
    gg1 <- gg1 + theme(legend.position = "bottom", legend.title = element_blank(), legend.text = element_text(size = 14), text = element_text(size = 14), axis.text = element_text(size = 12))
    gg1 <- gg1 + geom_line(aes(y = gen_key, color = "Geração das chaves"))
    gg1 <- gg1 + geom_line(aes(y = encrypt, color = "Criptografar"))
    gg1 <- gg1 + geom_line(aes(y = decrypt, color = "Descriptografar"))
    #Broke
    gg2 <- ggplot(allDataMerged, aes(key_size))
    gg2 <- gg2 + ggtitle("2) Quebra da chave")
    gg2 <- gg2 + xlab("Tamanho da chave (bits)")
    gg2 <- gg2 + ylab("Tempo (s)")
    gg2 <- gg2 + scale_y_continuous(breaks = round(seq(0, 100000, by = 10000), 1))
    gg2 <- gg2 + scale_x_continuous(breaks = round(seq(0, 140, by = 20), 1))
    gg2 <- gg2 + theme(legend.position = "bottom", legend.title = element_blank(), legend.text = element_text(size = 14), text = element_text(size = 14), axis.text = element_text(size = 12))
    gg2 <- gg2 + geom_line(aes(y = brute_force_mean_brute, color = "Força bruta"))
    gg2 <- gg2 + geom_line(aes(y = brute_force_mean_pollard, color = "Pollard"))
    grid.arrange(gg1, gg2, ncol = 2)
}